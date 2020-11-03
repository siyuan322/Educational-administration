package model;

public enum UserType {
    NONE("请选择", 0),
    ADMIN("系统管理员", 1),
    ACDEMICDEAN("教务员", 2),
    TEACHER("教师", 3),
    STUDENT("学生", 4);

    private String name;
    private int index;

    private UserType(String name, int index) {
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
        UserType[] userTypes = values();
        for (UserType userType : userTypes) {
            if (userType.getName().equals(str)) {
                return userType.getIndex();
            }
        }
        return -1;
    }
}
