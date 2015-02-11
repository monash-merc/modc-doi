<div class="page_style">
	<span class="total">Total ${paginationUsers.totalPages} Pages</span>
<#if paginationUsers.firstPage>
	<span class="disabled"> <img src="${base}/images/dis_first.png" class="img_position" /> Prev </span>
<#else>
	<a href="${base}/${pageLink}?pageNo=1"> <img src="${base}/images/first.png" class="img_position" /> First </a>
	<a href="${base}/${pageLink}?pageNo=${paginationUsers.prevPage?c}"> <img src="${base}/images/prev.png" class="img_position" /> Prev </a>
</#if>
<#if paginationUsers.pageNo-5 gt 1>
	<#if paginationUsers.totalPages gt paginationUsers.pageNo+4>
		<#list paginationUsers.pageNo-5..paginationUsers.pageNo+4 as i>
			<#if i == paginationUsers.pageNo>
				<span class="current">${i?c}</span>
			<#else>
				<a href="${base}/${pageLink}?pageNo=<#if i gt 0>${i?c}</#if>">${i?c}</a>
			</#if>
		</#list>
	<#else>
		<#list paginationUsers.totalPages-9..paginationUsers.totalPages as i>
			<#if i == paginationUsers.pageNo>
				<span class="current">${i?c}</span>
			<#else>
				<a href="${base}/${pageLink}?pageNo=<#if i gt 0>${i?c}</#if>">${i?c}</a>
			</#if>
		</#list>
	</#if>
<#else>
	<#if paginationUsers.totalPages gt 10>
		<#list 1..10 as i>
			<#if i == paginationUsers.pageNo>
				<span class="current">${i?c}</span>
			<#else>
				<a href="${base}/${pageLink}?pageNo=<#if i gt 0>${i?c}</#if>">${i?c}</a>
			</#if>
		</#list>
	<#else>
		<#list 1..paginationUsers.totalPages as i>
			<#if i == paginationUsers.pageNo>
				<span class="current">${i?c}</span>
			<#else>
				<a href="${base}/${pageLink}?pageNo=<#if i gt 0>${i?c}</#if>">${i?c}</a>
			</#if>
		</#list>
	</#if>
</#if>
<#if paginationUsers.lastPage>
	<span class="disabled"> Next <img src="${base}/images/dis_next.png" class="img_position" /> </span><span class="disabled"> Last <img src="${base}/images/dis_last.png" class="img_position" /> </span>
<#else>
	<a href="${base}/${pageLink}?pageNo=${paginationUsers.nextPage?c}"> Next <img src="${base}/images/next.png" class="img_position" /> </a>
	<a href="${base}/${pageLink}?pageNo=${paginationUsers.totalPages?c}"> Last <img src="${base}/images/last.png" class="img_position" /> </a>
</#if>
</div>