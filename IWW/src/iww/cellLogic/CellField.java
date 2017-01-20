package iww.cellLogic;

/**
 *
 * @author xasin
 */
public class CellField {
    private byte type;
    private byte[] metavalues = new byte[4];
    
    public byte nextType;
    public byte[] nextMetavalues = new byte[4];
    
    public CellField() {
    } 
    public CellField(CellField sauce) {
        this.type = sauce.type;
        this.nextType = sauce.nextType;
        System.arraycopy(sauce.metavalues, 0, this.metavalues, 0, 4);
        System.arraycopy(sauce.nextMetavalues, 0, this.nextMetavalues, 0, 4);
    }
    
    public void setType(byte type) {
        this.type = type;
        this.nextType = type;
    }
    public byte getType() {
        return this.type;
    }
    
    public byte[] getMetavalues() {
        byte[] newArray = new byte[4];
        System.arraycopy(this.metavalues, 0, newArray, 0, 4);
        return newArray;
    }
    public void setMetavalues(byte[] newMetas) {
        System.arraycopy(newMetas, 0, this.metavalues, 0, 4);
        System.arraycopy(newMetas, 0, this.nextMetavalues, 0, 4);
    }

    public void applyNextState() {
        this.type = nextType;
        System.arraycopy(this.nextMetavalues, 0, this.metavalues, 0, 4);
    }
}
