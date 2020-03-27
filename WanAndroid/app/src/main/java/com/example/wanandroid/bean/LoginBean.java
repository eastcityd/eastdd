package com.example.wanandroid.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author hwq
 * @date 2019/12/22.
 * GitHub：
 * Email：
 * Description：
 */
public class LoginBean implements Parcelable {

    /**
     * data : {"admin":false,"email":"","icon":"","id":54307,"nickname":"eastcityd","password":"","publicName":"eastcityd","token":"","type":0,"username":"eastcityd"}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean implements Parcelable {
        /**
         * admin : false
         * email :
         * icon :
         * id : 54307
         * nickname : eastcityd
         * password :
         * publicName : eastcityd
         * token :
         * type : 0
         * username : eastcityd
         */

        private boolean admin;
        private String email;
        private String icon;
        private int id;
        private String nickname;
        private String password;
        private String publicName;
        private String token;
        private int type;
        private String username;

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPublicName() {
            return publicName;
        }

        public void setPublicName(String publicName) {
            this.publicName = publicName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.admin ? (byte) 1 : (byte) 0);
            dest.writeString(this.email);
            dest.writeString(this.icon);
            dest.writeInt(this.id);
            dest.writeString(this.nickname);
            dest.writeString(this.password);
            dest.writeString(this.publicName);
            dest.writeString(this.token);
            dest.writeInt(this.type);
            dest.writeString(this.username);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.admin = in.readByte() != 0;
            this.email = in.readString();
            this.icon = in.readString();
            this.id = in.readInt();
            this.nickname = in.readString();
            this.password = in.readString();
            this.publicName = in.readString();
            this.token = in.readString();
            this.type = in.readInt();
            this.username = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
        dest.writeInt(this.errorCode);
        dest.writeString(this.errorMsg);
    }

    public LoginBean() {
    }

    protected LoginBean(Parcel in) {
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.errorCode = in.readInt();
        this.errorMsg = in.readString();
    }

    public static final Creator<LoginBean> CREATOR = new Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel source) {
            return new LoginBean(source);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };
}
