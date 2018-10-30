package com.langwing.samocharge._fragment._dynamic._wonderfulLife;


import com.langwing.samocharge._fragment._dynamic.bean.CircleItem;
import com.langwing.samocharge._fragment._dynamic.bean.CommentItem;
import com.langwing.samocharge._fragment._dynamic.bean.FavortItem;
import com.langwing.samocharge._fragment._dynamic.bean.PhotoInfo;
import com.langwing.samocharge._fragment._dynamic.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yiw
 * @ClassName: DatasUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015-12-28 下午4:16:21
 */
public class DatasUtil {


    public static final String[] CONTENTS = {
            "赣州到开州告诉路上有没有充电桩",
            "比亚迪秦混合动力的能充吗？",
            "快充充电桩一度电费用多少",
            "如何携桩入网",
            "私营公共充电桩都需要啥手续",
            "今天在郑州的某个充电站，充了一个小时，价格也不贵，非常实惠，推荐给大家。地址是：河南省郑州市高新区腊梅路，" +
                    "中国移动大厦，第 14 号楼,今天在郑州的某个充电站，充了一个小时，价格也不贵，非常实惠，推荐给大家。" +
                    "地址是：河南省郑州市高新区腊梅路，中国移动大厦，第 14 号楼",
            "郑州日产帅客可以充电吗，充满需要多长时间"
    };

    /**
     * 头像
     */
    public static final String[] HEADIMG = {
            "http://img.wzfzl.cn/uploads/allimg/140820/co140R00Q925-14.jpg",
            "http://www.feizl.com/upload2007/2014_06/1406272351394618.png",
            "http://v1.qzone.cc/avatar/201308/30/22/56/5220b2828a477072.jpg%21200x200.jpg",
            "http://v1.qzone.cc/avatar/201308/22/10/36/521579394f4bb419.jpg!200x200.jpg",
            "http://v1.qzone.cc/avatar/201408/20/17/23/53f468ff9c337550.jpg!200x200.jpg",
            "http://cdn.duitang.com/uploads/item/201408/13/20140813122725_8h8Yu.jpeg",
            "http://img.woyaogexing.com/touxiang/nv/20140212/9ac2117139f1ecd8%21200x200.jpg"};

    public static List<User> users = new ArrayList<User>();
    public static List<PhotoInfo> PHOTOS = new ArrayList<>();
    /**
     * 动态id自增长
     */
    private static int circleId = 0;
    /**
     * 点赞id自增长
     */
    private static int favortId = 0;
    /**
     * 评论id自增长
     */
    private static int commentId = 0;
    public static final User curUser = new User("0", "自己", HEADIMG[0]);

    static {
        User user1 = new User("1", "萨默测试用户 1", HEADIMG[1]);
        User user2 = new User("2", "萨默测试用户 2", HEADIMG[2]);
        User user3 = new User("3", "萨默测试用户 3", HEADIMG[3]);
        User user4 = new User("4", "萨默测试用户 4", HEADIMG[4]);
        User user5 = new User("5", "萨默测试用户 5", HEADIMG[5]);
        User user6 = new User("6", "这个名字是不是很长，哈哈！因为我是用来测试换行的", HEADIMG[6]);

        users.add(curUser);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);


        PhotoInfo p1 = new PhotoInfo();
        p1.url = "http://f.hiphotos.baidu.com/image/pic/item/faf2b2119313b07e97f760d908d7912396dd8c9c.jpg";
        p1.w = 640;
        p1.h = 792;

        PhotoInfo p2 = new PhotoInfo();
        p2.url = "http://g.hiphotos.baidu.com/image/pic/item/4b90f603738da977c76ab6fab451f8198718e39e.jpg";
        p2.w = 640;
        p2.h = 792;

        PhotoInfo p3 = new PhotoInfo();
        p3.url = "http://e.hiphotos.baidu.com/image/pic/item/902397dda144ad343de8b756d4a20cf430ad858f.jpg";
        p3.w = 950;
        p3.h = 597;

        PhotoInfo p4 = new PhotoInfo();
        p4.url = "http://a.hiphotos.baidu.com/image/pic/item/a6efce1b9d16fdfa0fbc1ebfb68f8c5495ee7b8b.jpg";
        p4.w = 533;
        p4.h = 800;

        PhotoInfo p5 = new PhotoInfo();
        p5.url = "http://b.hiphotos.baidu.com/image/pic/item/a71ea8d3fd1f4134e61e0f90211f95cad1c85e36.jpg";
        p5.w = 700;
        p5.h = 467;

        PhotoInfo p6 = new PhotoInfo();
        p6.url = "http://c.hiphotos.baidu.com/image/pic/item/7dd98d1001e939011b9c86d07fec54e737d19645.jpg";
        p6.w = 700;
        p6.h = 467;

        PhotoInfo p7 = new PhotoInfo();
        p7.url = "http://pica.nipic.com/2007-10-17/20071017111345564_2.jpg";
        p7.w = 1024;
        p7.h = 640;

        PhotoInfo p8 = new PhotoInfo();
        p8.url = "http://pic4.nipic.com/20091101/3672704_160309066949_2.jpg";
        p8.w = 1024;
        p8.h = 768;

        PhotoInfo p9 = new PhotoInfo();
        p9.url = "http://pic4.nipic.com/20091203/1295091_123813163959_2.jpg";
        p9.w = 1024;
        p9.h = 640;

        PhotoInfo p10 = new PhotoInfo();
        p10.url = "http://pic31.nipic.com/20130624/8821914_104949466000_2.jpg";
        p10.w = 1024;
        p10.h = 768;

        PHOTOS.add(p1);
        PHOTOS.add(p2);
        PHOTOS.add(p3);
        PHOTOS.add(p4);
        PHOTOS.add(p5);
        PHOTOS.add(p6);
        PHOTOS.add(p7);
        PHOTOS.add(p8);
        PHOTOS.add(p9);
        PHOTOS.add(p10);
    }

    public static List<CircleItem> createCircleDatas() {
        List<CircleItem> circleDatas = new ArrayList<CircleItem>();

        // 第一个动态

        CircleItem circleItem = new CircleItem();
        circleItem.setId("0");
        circleItem.setUser(users.get(0));
        circleItem.setContent(CONTENTS[0]);
        circleItem.setCreateTime("04月25日");
        circleItem.setFavorters(createFavortItemList());
        circleItem.setPhotos(createPhotos());

        // 第一个评论
        CommentItem commentItem = new CommentItem();
        User xz = new User("", "小战", "");
        commentItem.setUser(xz);
        commentItem.setToReplyUser(users.get(0));
        commentItem.setContent("充电快不快");

        List<CommentItem> replyComment = new ArrayList<>();
        // 老张回复小战
        CommentItem commentItem1 = new CommentItem();
        commentItem1.setToReplyUser(xz);
        commentItem1.setContent("我觉得挺快的，服务还好");
        commentItem1.setUser(new User("2", "老张", ""));
        replyComment.add(commentItem1);
        commentItem.setReplyCommentItemList(replyComment);


        // 第二个评论
        CommentItem commentItem2 = new CommentItem();
        commentItem2.setUser(new User("", "小明", ""));
        commentItem2.setToReplyUser(users.get(0));
        commentItem2.setContent("充电非常快，快来使用");

        // 评论集合
        List<CommentItem> commentItems = new ArrayList<>();
        commentItems.add(commentItem);
        commentItems.add(commentItem2);
        circleItem.setComments(commentItems);

        // 一条朋友圈
        circleDatas.add(circleItem);

//        for (int i = 0; i < 15; i++) {
//            CircleItem item = new CircleItem();
//            User user = getUser();
//            item.setId(String.valueOf(circleId++));
//            item.setUser(user);
//            item.setContent(getContent());
//            item.setCreateTime("12月24日");
//
//            item.setFavorters(createFavortItemList());
//            item.setComments(createCommentItemList());
//            item.setPhotos(createPhotos());
//
//            circleDatas.add(item);
//        }

        return circleDatas;
    }

    public static User getUser() {
        return users.get(getRandomNum(users.size()));
    }

    public static String getContent() {
        return CONTENTS[getRandomNum(CONTENTS.length)];
    }

    public static int getRandomNum(int max) {
        Random random = new Random();
        int result = random.nextInt(max);
        return result;
    }

    public static List<PhotoInfo> createPhotos() {
        List<PhotoInfo> photos = new ArrayList<PhotoInfo>();
        int size = getRandomNum(PHOTOS.size());
        if (size > 0) {
            if (size > 9) {
                size = 9;
            }
            for (int i = 0; i < size; i++) {
                PhotoInfo photo = PHOTOS.get(getRandomNum(PHOTOS.size()));
                if (!photos.contains(photo)) {
                    photos.add(photo);
                } else {
                    i--;
                }
            }
        }
        return photos;
    }

    public static List<FavortItem> createFavortItemList() {
        int size = getRandomNum(users.size());
        List<FavortItem> items = new ArrayList<FavortItem>();
        List<String> history = new ArrayList<String>();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                FavortItem newItem = createFavortItem();
                String userid = newItem.getUser().getId();
                if (!history.contains(userid)) {
                    items.add(newItem);
                    history.add(userid);
                } else {
                    i--;
                }
            }
        }
        return items;
    }

    public static FavortItem createFavortItem() {
        FavortItem item = new FavortItem();
        item.setId(String.valueOf(favortId++));
        item.setUser(getUser());
        return item;
    }


//    public static List<CommentItem> createCommentItemList() {
//        List<CommentItem> items = new ArrayList<CommentItem>();
//        int size = getRandomNum(10);
//        if (size > 0) {
//            for (int i = 0; i < size; i++) {
//                items.add(createComment());
//            }
//        }
//        return items;
//    }
//
//    public static CommentItem createComment() {
//        CommentItem item = new CommentItem();
//        item.setId(String.valueOf(commentId++));
//        item.setContent("哈哈");
//        User user = getUser();
//        item.setUser(user);
//        if (getRandomNum(10) % 2 == 0) {
//            while (true) {
//                User replyUser = getUser();
//                if (!user.getId().equals(replyUser.getId())) {
//                    item.setToReplyUser(replyUser);
//                    break;
//                }
//            }
//        }
//        return item;
//    }

    /**
     * 创建发布评论
     *
     * @return
     */
    public static CommentItem createPublicComment(String content) {
        CommentItem item = new CommentItem();
        item.setId(String.valueOf(commentId++));
        item.setContent(content);
        item.setUser(curUser);
        return item;
    }

    /**
     * 创建回复评论
     *
     * @return
     */
    public static CommentItem createReplyComment(User replyUser, String content) {
        CommentItem item = new CommentItem();
        item.setId(String.valueOf(commentId++));
        item.setContent(content);
        item.setUser(curUser);
        item.setToReplyUser(replyUser);
        return item;
    }
}
