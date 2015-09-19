package il.co.niv.avi.nofardictionary;

/**
 * Created by Avi on 19/09/2015.
 */
public class Word {

    private int mId;
    private String mHebrew;
    private String mEnglish;

    public Word(int id, String hebrew, String english) {
        mId = id;
        mHebrew = hebrew;
        mEnglish = english;
    }

    public int getId() { return mId; }

    public void setId(int id) { mId = id; }

    public String getHebrew() { return mHebrew; }

    public void setHebrew(String hebrew) { mHebrew = hebrew; }

    public String getEnglish() { return mEnglish; }

    public void setEnglish(String english) { mEnglish  = english;}
}
