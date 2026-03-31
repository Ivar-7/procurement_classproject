<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="grid gap-5 lg:grid-cols-3">
    <div class="panel p-5 lg:col-span-2">
        <div class="mb-4 flex flex-wrap items-center justify-between gap-3">
            <h1 class="text-xl font-semibold">Requisitions</h1>
            <a href="<c:url value='/requisitions/form'/>" class="rounded-md border border-stone-300 px-4 py-2 text-sm font-medium text-stone-700">
                Open Full Form
            </a>
        </div>

        <div class="overflow-x-auto">
            <table class="min-w-full text-sm">
                <thead>
                <tr class="border-b border-stone-200 text-left">
                    <th class="py-2">ID</th>
                    <th class="py-2">Req No.</th>
                    <th class="py-2">Dept ID</th>
                    <th class="py-2">Status</th>
                    <th class="py-2">Urgency</th>
                    <th class="py-2">Total</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="row" items="${requisitions}">
                    <tr class="border-b border-stone-100">
                        <td class="py-2">${row.reqId}</td>
                        <td class="py-2">${row.reqNumber}</td>
                        <td class="py-2">${row.deptId}</td>
                        <td class="py-2">${row.status}</td>
                        <td class="py-2">${row.urgency}</td>
                        <td class="py-2">${row.totalEstimated}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty requisitions}">
                    <tr>
                        <td colspan="6" class="py-4 text-center text-stone-500">No requisitions yet.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <div>
        <jsp:include page="form.jsp">
            <jsp:param name="embed" value="true"/>
        </jsp:include>
    </div>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
