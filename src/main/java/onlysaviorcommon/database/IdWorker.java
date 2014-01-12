package onlysaviorcommon.database;

/**
 * 毫秒级时间41位+机器ID 10位+毫秒内序列12位
 */
public class IdWorker {
    private final long workerId;
    private final static long twepoch = 1361753741828L;
    private final static long workerIdBits = 4L;
    public final static long maxWorkerId = ~-1L << workerIdBits;
    private final static long sequenceBits = 10L;

    private final static long workerIdShift = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    public final static long sequenceMask = ~-1L << sequenceBits;

    private long lastTimestamp = -1L;

    public IdWorker(final long workerId) {
        super();
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format(
                    "worker Id can't be greater than %d or less than 0",
                    maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        long sequence = 0L;
        if (this.lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if(sequence == 0)
                timestamp = this.tilNextMillis(this.lastTimestamp);
        } else {
            sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(
                        String.format(
                                "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                                this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        return ((timestamp - twepoch << timestampLeftShift))
                | (workerIdShift << this.workerId) | (sequence);
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }


    public static void main(String[] args){
        IdWorker worker2 = new IdWorker(2);
        System.out.println(worker2.nextId());


    }

}
