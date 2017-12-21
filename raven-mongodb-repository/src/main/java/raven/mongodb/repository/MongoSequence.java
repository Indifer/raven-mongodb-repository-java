package raven.mongodb.repository;

public class MongoSequence {

    private String sequenceName;

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    private String collectionName;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    private String incrementID;

    public String getIncrementID() {
        return incrementID;
    }

    public void setIncrementID(String incrementID) {
        this.incrementID = incrementID;
    }


    /**
     * @param sequence
     * @param collectionName
     * @param incrementID
     */
    public MongoSequence(final String sequence, final String collectionName, final String incrementID) {
        this.sequenceName = sequence;
        this.collectionName = collectionName;
        this.incrementID = incrementID;
    }

    /**
     *
     */
    public MongoSequence() {

        this.sequenceName = "_Sequence";
        this.collectionName = "_id";
        this.incrementID = "IncID";
    }
}
