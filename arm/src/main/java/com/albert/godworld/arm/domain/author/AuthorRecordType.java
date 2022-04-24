package com.albert.godworld.arm.domain.author;

public enum AuthorRecordType {

    CREATE_BOOK(1,"create book"),
    EDIT_BOOK(2,"edit book"),
    PUBLISH_BOOK(3,"publish book"),
    UN_PUBLISH_BOOK(4,"un_publish book"),

    PUBLISH_PART(5,"publish part"),
    PUBLISH_VOLUME(6,"publish volume"),
    PUBLISH_CHAPTER(7,"publish chapter"),
    EDIT_PART(8,"edit part"),
    EDIT_VOLUME(9,"edit volume"),
    EDIT_CHAPTER(10,"edit chapter"),
    REMOVE_PART(11,"remove part"),
    REMOVE_VOLUME(12,"remove volume"),
    REMOVE_CHAPTER(13,"remove chapter"),

    LIKE_COMMENT(14,"like comment"),
    UN_LIKE_COMMENT(15,"un like comment"),


    ;

    private final int code;
    private final String description;

    AuthorRecordType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
