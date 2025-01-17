package com.example.youtubeproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommentController {
    Comment comment;
    @FXML
    private ImageView profilePic;
    @FXML
    private Label userName;
    @FXML
    private Label likeNumber;
    @FXML
    private Label disLikeNumber;
    @FXML
    private Button likeButton;
    @FXML
    private Button disLikeButton;
    @FXML
    private Text commentText;

    public void setData(Comment comment) throws SQLException {
        this.comment = comment;
        if (!DatabaseManager.getAccount(comment.getAccountID()).getProfileImageAddress().equals(""))
            profilePic.setImage(new Image(DatabaseManager.getAccount(comment.getAccountID()).getProfileImageAddress()));
        else
            profilePic.setImage(new Image("file:/Users/ermiababaie/Documents/project/youTubeProject/src/main/resources/profile_icon.jpg"));
        userName.setText(DatabaseManager.getAccount(comment.getAccountID()).getUserName());
        List<LikeComment> likeCommentList = new ArrayList<>();
        likeCommentList = DatabaseManager.getCommentLikes(comment.getCommentID());
        int likeNum = 0;
        int disLikeNum = 0;
        for (LikeComment likeComment: likeCommentList) {
            if (likeComment.getLike() == 1)
                likeNum++;
            if (likeComment.getLike() == -1)
                disLikeNum++;
        }
        likeNumber.setText(String.valueOf(likeNum));
        disLikeNumber.setText(String.valueOf(disLikeNum));
        commentText.setText(comment.getText());
    }
    @FXML
    public void likeButtonClick() throws SQLException {
        if (YouTubeController.isLogin) {
            DatabaseManager.likeComment(YouTubeController.loginAccount.getAccountID(), comment.getCommentID(), 1);
        }
        List<LikeComment> likeCommentList = new ArrayList<>();
        likeCommentList = DatabaseManager.getCommentLikes(comment.getCommentID());
        int likeNum = 0;
        int disLikeNum = 0;
        for (LikeComment likeComment: likeCommentList) {
            if (likeComment.getLike() == 1)
                likeNum++;
            if (likeComment.getLike() == -1)
                disLikeNum++;
        }
        likeNumber.setText(String.valueOf(likeNum));
        disLikeNumber.setText(String.valueOf(disLikeNum));
    }
    @FXML
    public void disLikeButtonClick() throws SQLException {
        if (YouTubeController.isLogin) {
            DatabaseManager.likeComment(YouTubeController.loginAccount.getAccountID(), comment.getCommentID(), -1);
        }
        List<LikeComment> likeCommentList = new ArrayList<>();
        likeCommentList = DatabaseManager.getCommentLikes(comment.getCommentID());
        int likeNum = 0;
        int disLikeNum = 0;
        for (LikeComment likeComment: likeCommentList) {
            if (likeComment.getLike() == 1)
                likeNum++;
            if (likeComment.getLike() == -1)
                disLikeNum++;
        }
        likeNumber.setText(String.valueOf(likeNum));
        disLikeNumber.setText(String.valueOf(disLikeNum));
    }
}
