package com.Craffic.myshop.jersey.Utils;

import java.util.GregorianCalendar;

public class MessageGenerator {

    /**
     * 随机数字生成器
     */
    public static int randomNumGenerator(int size){
        if (size > 0){
            return (int)(Math.random() * size);
        } else {
            return 0;
        }
    }

    /**
     * 姓名生成器
     * @return
     */
    public static String randomBoyNameGenerator(int sex){

        // 百家姓
        String firstName = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻" +
                "柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤";

        String nameSingile = "";
        if (sex == 1 ){
            // 男生名字
            nameSingile = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生博诚先和彪" +
                    "龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚" +
                    "敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰" +
                    "鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧言若伯宏朗铭";
        } else if(sex == 0){
            nameSingile = "绛纯环颜清娴灵凌曦雨涵晗晴嫣沁瑞贝芷双琪欣茹菲茉茵诺妍樱舞芳嘉薰悠" +
                    "伊琳娟悦虞菱畅珊霏缨茵元源沅鸢瑗纭芸蕴昀筠若璐芷珉敏茗梦媚祁琦淇绮祺晴倾" +
                    "青箐磬希熙惜茜倩芊慧颜烟研雁嫣衍阳扬杨宜逸霖玲灵令凌菱翎绫泠苓聆栩诗絮煦" +
                    "暄瑾婉华眉端顺恬惠仁淳孝蓉敬景禧如庄容昭贞淑宸玫芩璇芙滢璃希萱瑛贤宁静德" +
                    "仁温敏甄绯纤韶雪薇仪伶艳柔兰月舒依云吟熙莫佳凝琴玉雯媛媚韵怡音恬静溪贞轩" +
                    "令嘉懿宝定盈曦黛宜愉如淑贤慕柔蕙宸成婉元文竹菊琦良彤端菲娴昌璇洪鸿颖裕敬" +
                    "玫祥吉沁全珊杰宁韵燕福华恩淳安珍瑛平绪蝶梦陈韶馨霖洁雨凌倾忻敏良淑承华怜" +
                    "羽舒纯瑞如荣豫依仪亦芩翊敬蓝伊吟萦茗锦金谨令祈顺若丽灵芷霜晋芳鄂多龙成尹" +
                    "敦梦柔瑶欣然哲穆孝雪凝和庄平婧岚白恂碧新怡蕴菱萍恭循洁徇悦楚江冰萱冷哗沫";
        }

        // 复姓
        String doubleSurName = "欧阳|太史|端木|上官|司马|东方|独孤|南宫|万俟|闻人|夏侯|诸葛|尉迟|" +
                "公羊|赫连|澹台|皇甫|宗政|濮阳|公冶|太叔|申屠|公孙|慕容|仲孙|钟离|长孙|宇文|司徒|" +
                "鲜于|司空|闾丘|子车|亓官|司寇|巫马|公西|颛孙|壤驷|公良|漆雕|乐正|宰父|谷梁|拓跋|" +
                "夹谷|轩辕|令狐|段干|百里|呼延|东郭|南门|羊舌|微生|公户|公玉|公仪|梁丘|公仲|公上|" +
                "公门|公山|公坚|左丘|公伯|西门|公祖|第五|公乘|贯丘|公皙|南荣|东里|东宫|仲长|子书|子桑|即墨|达奚|褚师|吴铭";
        String[] arrDoubleSurName = doubleSurName.split("\\|");

        int cntSurName = firstName.length();
        int cntDoubleSurName = arrDoubleSurName.length;
        int cntBoyNameSingle = nameSingile.length();

        StringBuffer name = new StringBuffer();
        int nameType = randomNumGenerator(4);
        if (nameType == 0){
            name.append(firstName.charAt(randomNumGenerator(cntSurName))).append(nameSingile.charAt(randomNumGenerator(cntBoyNameSingle)));
        } else if (nameType == 1){
            name.append(firstName.charAt(randomNumGenerator(cntSurName))).append(nameSingile.charAt(randomNumGenerator(cntBoyNameSingle))).append(nameSingile.charAt(randomNumGenerator(cntBoyNameSingle)));
        } else if (nameType == 2){
            name.append(arrDoubleSurName[randomNumGenerator(cntDoubleSurName)]).append(nameSingile.charAt(randomNumGenerator(cntBoyNameSingle)));
        }else if (nameType == 3){
            name.append(arrDoubleSurName[randomNumGenerator(cntDoubleSurName)]).append(nameSingile.charAt(randomNumGenerator(cntBoyNameSingle))).append(nameSingile.charAt(randomNumGenerator(cntBoyNameSingle)));
        }
        return name.toString();
    }

    /**
     *  邮箱随机生成器
     */
    public static String randomEmailGenerator(){
        String suffixEmailAll = "@gmail.com|@yahoo.com|@msn.com|@hotmail.com|@aol.com|@ask.com|@live.com|@qq.com|@0355.net|@163.com|@163.net|@263.net|@3721.net|@yeah";
        String[] arrSuffixEmail = suffixEmailAll.split("\\|");
        String letter  ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer prefixEmail = new StringBuffer();
        for (int i = 0; i < 5 ; i++) {
            prefixEmail.append(letter.charAt(randomNumGenerator(letter.length())));
        }
        String suffixEmail = arrSuffixEmail[randomNumGenerator(arrSuffixEmail.length)];
        return prefixEmail.append(suffixEmail).toString();
    }

    /**
     * 手机号码随机生成器
     */
    public static String randomPhoneNumGenerator(){
        String prefixPhoneNum = "134|136|137|138|139|150|151|152|188";
        String[] arrPrefixPhoneNum = prefixPhoneNum.split("\\|");
        String number = "1234567890";
        StringBuffer phone = new StringBuffer();
        phone.append(arrPrefixPhoneNum[randomNumGenerator(arrPrefixPhoneNum.length)]);
        for (int i = 0; i < 8; i++) {
            phone.append(number.charAt(randomNumGenerator(number.length())));
        }
        return phone.toString();
    }

    /**
     *算出两个数之间的随机数
     */
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    /**
     * 出生日期随机生成
     * @return
     */
    public static String randomBirthday() {

        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(1900, 2010);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        return gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH);
    }
}
