/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Jakob
 */
public class Article {

    @Column(name = "article_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int articleID;
    private String name;
    private String description;
    @Column(length = 1)
    private char sex;

    public Article() {
    }

    public Article(String name, String description, char sex) {
        this.name = name;
        this.description = description;
        this.sex = sex;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
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

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
