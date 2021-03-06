/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author Jakob
 */
@Entity
@NamedQuery(name = "Article.getAllArticle", query = "Select a FROM Article a")
public class Article {

    @Column(name = "article_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int articleID;
    private String name;
    private String description;
    @Column(length = 1)
    private char sex;
    private String brand;
    private String pic;

    public Article() {
    }

    public Article(int articleID, String name, String description, char sex, String brand, String pic)
    {
        this.articleID = articleID;
        this.name = name;
        this.description = description;
        this.sex = sex;
        this.brand = brand;
        this.pic = pic;
    }
   public Article(String name, String description, char sex, String brand, String pic)
    {
        this.name = name;
        this.description = description;
        this.sex = sex;
        this.brand = brand;
        this.pic = pic;
    }
    public String getBrand()
    {
        return brand;
        
       
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getPic()
    {
        return pic;
    }

    public void setPic(String pic)
    {
        this.pic = pic;
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
