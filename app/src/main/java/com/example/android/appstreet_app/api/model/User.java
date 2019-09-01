package com.example.android.appstreet_app.api.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {


    /**
     * username : chrisbanes
     * name : Chris Banes
     * type : user
     * url : https://github.com/chrisbanes
     * avatar : https://avatars0.githubusercontent.com/u/227486
     * repo : {"name":"PhotoView","description":"Implementation of ImageView for Android that supports zooming, by various touch gestures.","url":"https://github.com/chrisbanes/PhotoView"}
     */

    public String username;
    public String name;
    public String type;
    public String url;
    public String avatar;
    public RepoBean repo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public RepoBean getRepo() {
        return repo;
    }

    public void setRepo(RepoBean repo) {
        this.repo = repo;
    }

    public static class RepoBean implements Parcelable {
        /**
         * name : PhotoView
         * description : Implementation of ImageView for Android that supports zooming, by various touch gestures.
         * url : https://github.com/chrisbanes/PhotoView
         */

        public String name;
        public String description;
        public String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "RepoBean{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.description);
            dest.writeString(this.url);
        }

        public RepoBean() {
        }

        protected RepoBean(Parcel in) {
            this.name = in.readString();
            this.description = in.readString();
            this.url = in.readString();
        }

        public static final Creator<RepoBean> CREATOR = new Creator<RepoBean>() {
            @Override
            public RepoBean createFromParcel(Parcel source) {
                return new RepoBean(source);
            }

            @Override
            public RepoBean[] newArray(int size) {
                return new RepoBean[size];
            }
        };
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", avatar='" + avatar + '\'' +
                ", repo=" + repo +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeString(this.avatar);
        dest.writeParcelable(this.repo, flags);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.username = in.readString();
        this.name = in.readString();
        this.type = in.readString();
        this.url = in.readString();
        this.avatar = in.readString();
        this.repo = in.readParcelable(RepoBean.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
