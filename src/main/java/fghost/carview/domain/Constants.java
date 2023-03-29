package fghost.carview.domain;


import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    //USERS
    public static final String USER_NOT_PERSISTED = "user.not.persisted";
    public static final String USER_NOT_FOUND = "user.not.found";
    public static final String USER_PHOTO_NOT_FOUND = "user.photo.not.found";
    public static final String USER_PHOTO_NOT_PERSISTED = "user;photo.not.persisted";
    public static final String USER_DELETION_ERROR = "user.deletion.error";

    //ACCESS GROUPS
    public static final String ACCESS_GROUP_NOT_FOUND = "access.group.not.found";
    public static final String ACCESS_GROUP_DUPLICATED = "access.group.duplicated";
    public static final String ACCESS_GROUP_NOT_PERSISTED = "access.group.not.persisted";

    //PROFILES
    public static final String PROFILE_NOT_FOUND = "profile.not.found";
    public static final String PROFILE_DUPLICATED = "profile.duplicated";
    public static final String PROFILE_NOT_PERSISTED = "profile.not.persisted";
    public static final String PROFILE_DELETION_ERROR = "profile.deletion.error";

    //STORAGE
    public static final String STORAGE_NOT_PERSISTED = "storage.not.persisted";
    public static final String STORAGE_OBJECT_NOT_FOUND = "storage.object.not.found";
    public static final String STORAGE_READ_ERROR = "storage.read.error";
    public static final String STORAGE_DELETE_ERROR = "storage.delete.error";
    public static final String STORAGE_ROOT_ERROR = "storage.root.error";

    //CAR
    public static final String CAR_DUPLICATED = "car.duplicated";
    public static final String CAR_NOT_FOUND = "car.not.found";
}
