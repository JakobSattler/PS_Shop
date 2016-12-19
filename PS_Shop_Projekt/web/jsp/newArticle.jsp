<%-- 
    Document   : newArticle
    Created on : 19.12.2016, 15:40:21
    Author     : jauma_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
  
        <title>Neuen Artikel hinzufügen!</title>
    </head>
    <body>
        <h1>Artikel hinzufügen</h1>
        <form action="../HomeServlet">
            <div class="row">
                <div class="col-md-1">Artikelname:</div>
                <div class="col-md-1"><input type="text" name="tfArtikelname" value="" /></div>
            </div>
            <div class="row">
                <div class="col-md-1">Artikelbeschreibung:</div>
                <div class="col-md-1"><input type="text" name="tfArtikelbeschreibung" value="" /></div>
            </div>
            <div class="row">
                <div class="col-md-1">Geschlecht(m oder w):</div>
                <div class="col-md-1"><input type="text" name="tfSex" value="" /></div>
            </div>
            <div class="row">
                <div class="col-md-1"><input type="submit" value="Hinzufuegen" /></div>              
            </div>
        </form>
    </body>
</html>
