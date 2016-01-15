package Twitter4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;

public class Twitter4jTutorial {
    public static void main(String[] args){
        try{
            //自動的に認証してくれる
            //（バージョン2.2.4以降はgetInstance()ではなくgetSingleton()を推奨）
            new TwitterStreamFactory();
            TwitterStream twitterstream = TwitterStreamFactory.getSingleton();
            twitterstream.addListener(new MyStatusAdapter());

            //全体の1%のデータを取得
            twitterstream.sample();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
//コンソールに出力する
class MyStatusAdapter extends StatusAdapter {
    public void onStatus(Status status){
        //ユーザの情報を取得
        User user = status.getUser();
        //日本のもののみを抽出
        if (containsNihongo(status.getText()) == true){

        //CSV出力用
        	//write(status,user);


        //ツイートから改行記号を除去（半角スペースに変換）
        String strText = status.getText();
        strText = strText.replaceAll("\r\n"," ");
        strText = strText.replaceAll("\r"," ");
        strText = strText.replaceAll("\n"," ");
        //ツイートからタブ記号を除去（半角スペースに変換）
        strText = strText.replaceAll("\t"," ");
        //コンソールに出力する（タイムゾーン、位置情報、日時、ユーザ名、ツイート）
        System.out.println(user.getTimeZone() + "\t" + status.getGeoLocation() + "\t" + status.getCreatedAt() + "\t" + user.getScreenName() + "\t" + strText);
        //System.out.println(status.getCreatedAt() + "\t" + user.getScreenName() + "\t" + strText);
    }
  }
    public static boolean containsNihongo(String str) {
        for(int i = 0 ; i < str.length() ; i++) {
            char ch = str.charAt(i);
            Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
            if (Character.UnicodeBlock.HIRAGANA.equals(unicodeBlock))
                return true;
            if (Character.UnicodeBlock.KATAKANA.equals(unicodeBlock))
                return true;
            if (Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS.equals(unicodeBlock))
                return true;
            if (Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(unicodeBlock))
                return true;
            if (Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION.equals(unicodeBlock))
                return true;
        }

        return false;
    }

    public void write(Status status,User user) {


    		  Calendar date = Calendar.getInstance();
    		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
    	    try {
    	        File csv = new File("Tweet_"+sdf.format(date.getTime())+".csv"); // CSVデータファイル
    	    //	File csv = new File("test.csv"); // CSVデータファイル



    	      // 追記モード
    	      BufferedWriter bw
    	        = new BufferedWriter(new FileWriter(csv, true));
    	      // 新たなデータ行の追加
    	        //ツイートから改行記号を除去（半角スペースに変換）

    	        String strText = status.getText();
    	        strText = strText.replaceAll("\r\n"," ");
    	        strText = strText.replaceAll("\r"," ");
    	        strText = strText.replaceAll("\n"," ");
    	        //ツイートからタブ記号を除去（半角スペースに変換）
    	        strText = strText.replaceAll("\t"," ");
    	        //カンマを半角スペースに変換
    	        strText = strText.replaceAll(","," ");

    	      bw.write(user.getTimeZone() + "," +status.getGeoLocation() + "," +status.getCreatedAt()+ "," +user.getScreenName()+ ","+strText);
    	      bw.newLine();
    	      bw.close();

    	    } catch (FileNotFoundException er) {
    	      // Fileオブジェクト生成時の例外捕捉
    	      er.printStackTrace();
    	    } catch (IOException er) {
    	      // BufferedWriterオブジェクトのクローズ時の例外捕捉
    	      er.printStackTrace();
    	  }
    	}



}