<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="embedded" value="${param.embed eq 'true'}"/>
<c:set var="isEdit" value="${not empty formDepartment and not empty formDepartment.deptId}"/>

<c:if test="${not embedded}">
    <jsp:include page="/WEB-INF/views/components/header.jspf"/>
</c:if>

<section class="panel p-5">
    <div class="mb-4 flex flex-wrap items-center justify-between gap-3">
        <h1 class="text-xl font-semibold">
            <c:choose>
                <c:when test="${isEdit}">Edit Department</c:when>
                <c:otherwise>Department Form</c:otherwise>
            </c:choose>
        </h1>
        <c:if test="${not embedded}">
            <a href="<c:url value='/departments'/>" class="rounded-md border border-stone-300 px-4 py-2 text-sm font-medium text-stone-700">
                Back To List
            </a>
        </c:if>
    </div>

    <form method="post" action="<c:url value='/departments'/>" class="space-y-3">
        <c:if test="${isEdit}">
            <input type="hidden" name="deptId" value="${formDepartment.deptId}" />
        </c:if>

        <div>
            <label class="mb-1 block text-sm font-medium">Department Name</label>
            <input name="deptName" required value="${formDepartment.deptName}" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
        </div>
        <div>
            <label class="mb-1 block text-sm font-medium">Budget Code</label>
            <input name="budgetCode" value="${formDepartment.budgetCode}" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
        </div>
        <div>
            <label class="mb-1 block text-sm font-medium">Annual Budget</label>
            <input name="annualBudget" value="${formDepartment.annualBudget}" type="number" step="0.01" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
        </div>
        <div>
            <label class="mb-1 block text-sm font-medium">Head Name</label>
            <input name="headName" value="${formDepartment.headName}" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
        </div>
        <div>
            <label class="mb-1 block text-sm font-medium">Email</label>
            <input name="email" value="${formDepartment.email}" type="email" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
        </div>

        <div class="flex items-center gap-2">
            <button class="rounded-md bg-teal-700 px-4 py-2 text-sm font-medium text-white">
                <c:choose>
                    <c:when test="${isEdit}">Update Department</c:when>
                    <c:otherwise>Save Department</c:otherwise>
                </c:choose>
            </button>

            <c:if test="${isEdit}">
                <a href="<c:url value='/departments'/>" class="rounded-md border border-stone-300 px-4 py-2 text-sm font-medium text-stone-700">
                    Cancel Edit
                </a>
            </c:if>
        </div>
    </form>
</section>

<c:if test="${not embedded}">
    <jsp:include page="/WEB-INF/views/components/footer.jspf"/>
</c:if>
