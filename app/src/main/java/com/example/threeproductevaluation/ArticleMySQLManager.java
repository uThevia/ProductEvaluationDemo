package com.example.threeproductevaluation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleMySQLManager {

    private static final String TAG ="ArticleMySQLManager";
    private SqlHelper sqlHelper;

    public ArticleMySQLManager(){
        sqlHelper = new SqlHelper();
    }

    //查
    //显示所有数据
    public List<ArticleItem> search(){
        List<ArticleItem> list = null;
        String sql = "select * from Article;";
        ResultSet resultSet = sqlHelper.onQuery(sql);
        if(resultSet == null){
            Log.i(TAG, "resultSet == null");
            return null;
        }else {
            try {
                list = new ArrayList<ArticleItem>();
                while (resultSet.next()) {
                    ArticleItem articleItem = new ArticleItem();
                    articleItem.setId(resultSet.getInt("Article_ID"));
                    articleItem.setUrl(resultSet.getString("Article_Url"));
                    articleItem.setTitle(resultSet.getString("Article_Title"), "none");
                    articleItem.setDetail(resultSet.getString("Article_Detail"), "none");
                    list.add(articleItem);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Log.i(TAG, "fail to search Article all");
            }

        }
        return list;
    }
    public ArticleItem search(int id) {
        ArticleItem articleItem = null;
        String sql = "select * from Article where Article_ID = " + id + ";";
        ResultSet resultSet = sqlHelper.onQuery(sql);
        if (resultSet == null) {
            Log.i(TAG, "resultSet == null");
            return null;
        } else {
            try {
                if (resultSet.next()) {
                    articleItem.setId(resultSet.getInt("Article_ID"));
                    articleItem.setUrl(resultSet.getString("Article_Url"));
                    articleItem.setTitle(resultSet.getString("Article_Title"), "none");
                    articleItem.setDetail(resultSet.getString("Article_Detail"), "none");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Log.i(TAG, "fail to search Article all");
            }
            return articleItem;
        }
    }

    public void finish(){
        if (sqlHelper != null)
            sqlHelper.onFinish();
    }
}
