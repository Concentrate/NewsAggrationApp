package com.interestcontent.liudeyu.contents.zhihu.bean;

import com.interestcontent.liudeyu.base.baseBeans.FeedBaseBean;
import com.interestcontent.liudeyu.base.baseBeans.BaseRequest;

import java.util.List;

/**
 * Created by liudeyu on 2018/2/7.
 */

public class ZhihuJournayListRequest extends BaseRequest {

    /**
     * date : 20180207
     * stories : [{"images":["https://pic1.zhimg.com/v2-4d9e250f4ab5bcc383323728295309a0.jpg"],"type":0,"id":9670282,"ga_prefix":"020715","title":"为什么部分中国女性不刮腋毛？"},{"images":["https://pic1.zhimg.com/v2-7a2c17539bac551c7309faf3b1037138.jpg"],"type":0,"id":9670272,"ga_prefix":"020713","title":"SpaceX 猎鹰重型火箭送了辆跑车上天，它到底牛在哪？"},{"images":["https://pic1.zhimg.com/v2-7ec5b1e28ead516bd2fc7c8705d94eb8.jpg"],"type":0,"id":9670293,"ga_prefix":"020713","title":"台湾花莲发生 6.5 级地震，罕见的频繁小地震 2 月 4 日就开始了"},{"images":["https://pic4.zhimg.com/v2-3c5a5cf711f717656df75d36bdc15f53.jpg"],"type":0,"id":9670176,"ga_prefix":"020712","title":"大误 · 神奇宝贝为何不统治人类？"},{"images":["https://pic2.zhimg.com/v2-c38dfcfe5ba3a61b1499412465fb5aa1.jpg"],"type":0,"id":9670223,"ga_prefix":"020711","title":"女儿因早恋被父亲打至骨折，里面的「中国式家长思维」才是最可怕的"},{"images":["https://pic2.zhimg.com/v2-deee0bfdd6b138fa8295fa3f77bffb69.jpg"],"type":0,"id":9668305,"ga_prefix":"020710","title":"记忆力不如以前，总说自己老了，其实是有件事你做的少了"},{"images":["https://pic1.zhimg.com/v2-d27ca93d9854ba739418ad2f353596e8.jpg"],"type":0,"id":9670117,"ga_prefix":"020709","title":"每天就是试试衣服、发发照片，时尚博主怎么赚到钱的？"},{"images":["https://pic3.zhimg.com/v2-ec061643b7be8231cf8c31023cdd4e32.jpg"],"type":0,"id":9670110,"ga_prefix":"020708","title":"上海滩电竞风云"},{"images":["https://pic1.zhimg.com/v2-1fed9f3d064521e51153aca6573a85d0.jpg"],"type":0,"id":9670101,"ga_prefix":"020707","title":"为什么年轻人越来越反感「亲戚」这群人？"},{"images":["https://pic1.zhimg.com/v2-b307b9b0795575cbb8b275733e4cb910.jpg"],"type":0,"id":9670172,"ga_prefix":"020707","title":"- 加湿器会让屋里 PM 2.5 增加吗？\r\n- 有可能"},{"images":["https://pic4.zhimg.com/v2-94a3ce047c8dc10c4fbd9e9ad629b66f.jpg"],"type":0,"id":9670170,"ga_prefix":"020707","title":"披着加密货币外衣的「传销」骗局"},{"images":["https://pic2.zhimg.com/v2-e7bbd419d2d2bb4edc8655dd6836523d.jpg"],"type":0,"id":9670053,"ga_prefix":"020706","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic4.zhimg.com/v2-a0a38c2677aa0b5c9c1ce2078daf3f83.jpg","type":0,"id":9670272,"ga_prefix":"020713","title":"SpaceX 猎鹰重型火箭送了辆跑车上天，它到底牛在哪？"},{"image":"https://pic4.zhimg.com/v2-52b015cd264a0137cc764aab1251ba67.jpg","type":0,"id":9670282,"ga_prefix":"020715","title":"为什么部分中国女性不刮腋毛？"},{"image":"https://pic2.zhimg.com/v2-609ca01e1543b92f5b3445478ee1d345.jpg","type":0,"id":9670293,"ga_prefix":"020713","title":"台湾花莲发生 6.5 级地震，罕见的频繁小地震 2 月 4 日就开始了"},{"image":"https://pic3.zhimg.com/v2-7d5e8edfa603d653656be5b5d6e48a1e.jpg","type":0,"id":9670223,"ga_prefix":"020711","title":"女儿因早恋被父亲打至骨折，里面的「中国式家长思维」才是最可怕的"},{"image":"https://pic1.zhimg.com/v2-7daf57c817123e7df40f4ff0978d7dd0.jpg","type":0,"id":9670170,"ga_prefix":"020707","title":"披着加密货币外衣的「传销」骗局"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class TopStoriesBean extends FeedBaseBean{
        /**
         * image : https://pic4.zhimg.com/v2-a0a38c2677aa0b5c9c1ce2078daf3f83.jpg
         * type : 0
         * id : 9670272
         * ga_prefix : 020713
         * title : SpaceX 猎鹰重型火箭送了辆跑车上天，它到底牛在哪？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
