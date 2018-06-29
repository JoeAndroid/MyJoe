package com.common.utils.bean;

import java.util.List;

/**
 * 首页数据
 * Created by qiaobing on 2017/12/15.
 */
public class HomeBean {

    private List<CourseBean> course;
    private List<VideoBean> video;
    private List<InfoBean> info;
    private List<ActivityBean> activity;
    private HistoryBean history;

    private String type;

    public List<CourseBean> getCourse() {
        return course;
    }

    public void setCourse(List<CourseBean> course) {
        this.course = course;
    }

    public List<VideoBean> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBean> video) {
        this.video = video;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public List<ActivityBean> getActivity() {
        return activity;
    }

    public void setActivity(List<ActivityBean> activity) {
        this.activity = activity;
    }

    public HistoryBean getHistory() {
        return history;
    }

    public void setHistory(HistoryBean history) {
        this.history = history;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class CourseBean {


        private int id;
        private int userId;
        private int type;
        private int status;
        private String name;
        private String description;
        private String crowd;
        private String teachTime;
        private String place;
        private String organ;
        private int cost;
        private String contact;
        private String duration;
        private int needPeoples;
        private long startTime;
        private String img1;
        private String img2;
        private String img3;
        private long createTime;
        private int buyPeoples;
        private int hits;
        private int commentNum;
        private int collectNum;
        private int cityId;
        private int isEs;
        private String courseCreateTime;
        private String img1Path;
        private String img2Path;
        private String img3Path;
        private int sellStatus;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

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

        public String getCrowd() {
            return crowd;
        }

        public void setCrowd(String crowd) {
            this.crowd = crowd;
        }

        public String getTeachTime() {
            return teachTime;
        }

        public void setTeachTime(String teachTime) {
            this.teachTime = teachTime;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getOrgan() {
            return organ;
        }

        public void setOrgan(String organ) {
            this.organ = organ;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public int getNeedPeoples() {
            return needPeoples;
        }

        public void setNeedPeoples(int needPeoples) {
            this.needPeoples = needPeoples;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public String getImg2() {
            return img2;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public String getImg3() {
            return img3;
        }

        public void setImg3(String img3) {
            this.img3 = img3;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getBuyPeoples() {
            return buyPeoples;
        }

        public void setBuyPeoples(int buyPeoples) {
            this.buyPeoples = buyPeoples;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getIsEs() {
            return isEs;
        }

        public void setIsEs(int isEs) {
            this.isEs = isEs;
        }

        public String getCourseCreateTime() {
            return courseCreateTime;
        }

        public void setCourseCreateTime(String courseCreateTime) {
            this.courseCreateTime = courseCreateTime;
        }

        public String getImg1Path() {
            return img1Path;
        }

        public void setImg1Path(String img1Path) {
            this.img1Path = img1Path;
        }

        public String getImg2Path() {
            return img2Path;
        }

        public void setImg2Path(String img2Path) {
            this.img2Path = img2Path;
        }

        public String getImg3Path() {
            return img3Path;
        }

        public void setImg3Path(String img3Path) {
            this.img3Path = img3Path;
        }

        public int getSellStatus() {
            return sellStatus;
        }

        public void setSellStatus(int sellStatus) {
            this.sellStatus = sellStatus;
        }
    }

    public static class VideoBean {

        private int id;
        private String name;
        private String url;
        private String img;
        private long createTime;
        private int userId;
        private int duration;
        private int hits;
        private int praiseNum;
        private int collectNum;
        private int commentNum;
        private int shareNum;
        private String uploadAli;
        private int cdn;
        private String userName;
        private int state;
        private int isEs;
        private String videoCreateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getDuration() {
            String hour = null;
            String fen = null;
            String miao = null;
            if (duration > 3600) {
                hour = duration / 3600 + "";
                hour = hour.length() > 1 ? hour : 0 + hour;
                fen = duration % 3600 / 60 + "";
                fen = fen.length() > 1 ? fen : 0 + fen;
                miao = duration % 3600 % 60 + "";
                miao = miao.length() > 1 ? miao : 0 + miao;
            } else if (duration > 60) {
                fen = duration / 60 + "";
                miao = duration % 60 + "";
                miao = miao.length() > 1 ? miao : 0 + miao;
            } else {
                miao = duration + "";
                miao = miao.length() > 1 ? miao : 0 + miao;
            }
            if (hour != null) {
                return hour + ":" + fen + ":" + miao;
            } else if (fen != null) {
                return fen + ":" + miao;
            } else {
                return "00:" + miao;
            }
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getShareNum() {
            return shareNum;
        }

        public void setShareNum(int shareNum) {
            this.shareNum = shareNum;
        }

        public String getUploadAli() {
            return uploadAli;
        }

        public void setUploadAli(String uploadAli) {
            this.uploadAli = uploadAli;
        }

        public int getCdn() {
            return cdn;
        }

        public void setCdn(int cdn) {
            this.cdn = cdn;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getIsEs() {
            return isEs;
        }

        public void setIsEs(int isEs) {
            this.isEs = isEs;
        }

        public String getVideoCreateTime() {
            return videoCreateTime;
        }

        public void setVideoCreateTime(String videoCreateTime) {
            this.videoCreateTime = videoCreateTime;
        }
    }

    public static class InfoBean {

        private int id;
        private String title;
        private String details;
        private long createTime;
        private String img;
        private int praiseNum;
        private int collectNum;
        private int commentNum;
        private int shareNum;
        private int hits;
        private int isCollect;
        private int isPraise;
        private String createTimeStr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getShareNum() {
            return shareNum;
        }

        public void setShareNum(int shareNum) {
            this.shareNum = shareNum;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public int getIsCollect() {
            return isCollect;
        }

        public void setIsCollect(int isCollect) {
            this.isCollect = isCollect;
        }

        public int getIsPraise() {
            return isPraise;
        }

        public void setIsPraise(int isPraise) {
            this.isPraise = isPraise;
        }

        public String getCreateTimeStr() {
            return createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }
    }

    public static class HistoryBean {

        private List<OriginalBean> original;

        public List<OriginalBean> getOriginal() {
            return original;
        }

        public void setOriginal(List<OriginalBean> original) {
            this.original = original;
        }

        public static class OriginalBean {

            private int id;
            private String name;
            private String url;
            private String img;
            private long createTime;
            private int userId;
            private int duration;
            private int hits;
            private int praiseNum;
            private int collectNum;
            private int commentNum;
            private int shareNum;
            private String uploadAli;
            private int cdn;
            private String userName;
            private int state;
            private int isEs;
            private String videoCreateTime;
            private String columns;
            private String descript;
            private String label;
            private String videoWidth;
            private String videoHeight;
            private int liveId;
            private long lastUpdateTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getHits() {
                return hits;
            }

            public void setHits(int hits) {
                this.hits = hits;
            }

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
            }

            public int getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(int collectNum) {
                this.collectNum = collectNum;
            }

            public int getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(int commentNum) {
                this.commentNum = commentNum;
            }

            public int getShareNum() {
                return shareNum;
            }

            public void setShareNum(int shareNum) {
                this.shareNum = shareNum;
            }

            public String getUploadAli() {
                return uploadAli;
            }

            public void setUploadAli(String uploadAli) {
                this.uploadAli = uploadAli;
            }

            public int getCdn() {
                return cdn;
            }

            public void setCdn(int cdn) {
                this.cdn = cdn;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getIsEs() {
                return isEs;
            }

            public void setIsEs(int isEs) {
                this.isEs = isEs;
            }

            public String getVideoCreateTime() {
                return videoCreateTime;
            }

            public void setVideoCreateTime(String videoCreateTime) {
                this.videoCreateTime = videoCreateTime;
            }

            public String getColumns() {
                return columns;
            }

            public void setColumns(String columns) {
                this.columns = columns;
            }

            public String getDescript() {
                return descript;
            }

            public void setDescript(String descript) {
                this.descript = descript;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getVideoWidth() {
                return videoWidth;
            }

            public void setVideoWidth(String videoWidth) {
                this.videoWidth = videoWidth;
            }

            public String getVideoHeight() {
                return videoHeight;
            }

            public void setVideoHeight(String videoHeight) {
                this.videoHeight = videoHeight;
            }

            public int getLiveId() {
                return liveId;
            }

            public void setLiveId(int liveId) {
                this.liveId = liveId;
            }

            public long getLastUpdateTime() {
                return lastUpdateTime;
            }

            public void setLastUpdateTime(long lastUpdateTime) {
                this.lastUpdateTime = lastUpdateTime;
            }
        }
    }

    public static class ActivityBean {

        /**
         * id : 1
         * title : abc
         * enrollStartTime : 232323
         * enrollEndTime : 4565545454
         * venue : 北京
         * rules : 121223dfddfcv
         * createTime : 1513667198971
         * activityCreateTime : 2017-12-19
         */

        private int id;
        private String title;
        private String img;
        private int enrollStartTime;
        private long enrollEndTime;
        private String venue;
        private String rules;
        private long createTime;
        private String activityCreateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getEnrollStartTime() {
            return enrollStartTime;
        }

        public void setEnrollStartTime(int enrollStartTime) {
            this.enrollStartTime = enrollStartTime;
        }

        public long getEnrollEndTime() {
            return enrollEndTime;
        }

        public void setEnrollEndTime(long enrollEndTime) {
            this.enrollEndTime = enrollEndTime;
        }

        public String getVenue() {
            return venue;
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

        public String getRules() {
            return rules;
        }

        public void setRules(String rules) {
            this.rules = rules;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getActivityCreateTime() {
            return activityCreateTime;
        }

        public void setActivityCreateTime(String activityCreateTime) {
            this.activityCreateTime = activityCreateTime;
        }
    }
}

