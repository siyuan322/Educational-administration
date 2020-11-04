package model;

public enum StudyTimeType {
    None("请选择", 0),
    MonMor("周一上午", 1), MonAfter("周一下午", 2),
    TueMor("周二上午", 3), TueAfter("周二下午", 4),
    WedMor("周三上午", 5), WedAfter("周三下午", 6),
    ThurMor("周四上午", 7), ThurAfter("周四下午", 8),
    FriMor("周五上午", 9), FriAfter("周五下午", 10);

    private String name;
    private int index;

    private StudyTimeType(String name, int index) {
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
        StudyTimeType[] studyTimesEnums = values();
        for (StudyTimeType studyTime : studyTimesEnums) {
            if (studyTime.getName().equals(str)) {
                return studyTime.getIndex();
            }
        }
        return -1;
    }
}
