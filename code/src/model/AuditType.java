package model;

public enum AuditType {
    NONE("请选择", 0),
    UNDO("未审核", 1),
    PASSED("审核通过", 2),
    UNPASSED("审核不通过", 3);

    private String name;
    private int index;

    private AuditType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return name;
    }

    public static int getIndex(String str) {
        AuditType[] auditTypes = values();
        for (AuditType auditType : auditTypes) {
            if (auditType.getName().equals(str)) {
                return auditType.getIndex();
            }
        }
        return -1;
    }

    public static String getName(int index) {
        AuditType[] auditTypes = values();
        for (AuditType auditType : auditTypes) {
            if (auditType.getIndex() == index) {
                return auditType.getName();
            }
        }
        return null;
    }
}
