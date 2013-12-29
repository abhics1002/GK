package com.abhi.gk;

public class RowItem {
    private int imageId;
    private String question;
//    private String userAnswer;
//    private String correctAnswer;
 
    public RowItem(int imageId, String question/*, String userAnswer, String correctAnswer*/) {
        this.imageId = imageId;
        this.question = question;
//        this.userAnswer = userAnswer;
//        this.correctAnswer = correctAnswer;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    
   /* public String getuserAnswer() {
        return userAnswer;
    }
    public void setuserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }*/
    public String getquestion() {
        return question;
    }
    public void setquestion(String question) {
        this.question = question;
    }
    
   /* public String getcorrectAnswer()
    {
    	return correctAnswer;
    }
    public void setcorrectAnswer(String correctAnswer)
    {
    	this.correctAnswer = correctAnswer;
    }*/
    /* @Override
    public String toString() {
        return title + "\n" + desc +"\n" + correct_answer;
    }*/
}
