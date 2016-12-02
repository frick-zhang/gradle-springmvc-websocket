<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
  <h2></h2>
  <h3></h3>
  <ol class="spittle-list">
    <c:forEach var="spittle" items="${spittles}">
      <s:url value="/spitters/{spitterName}" var="spitter_url">
        <s:param name="spitterName" value="${spittle.spitter.username }"/>
      </s:url>
      <li>
        <span class="spittleListImage">
          <img alt="" src="" width="48" border="0" align="middle">
        </span>
        <span class="spittleListText">
          <a href="${spitter_url }"></a>
        </span>
      </li>
    </c:forEach>
  </ol>
</div>