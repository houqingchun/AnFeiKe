<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<p><strong>${COMPANY_OBJ.name}</strong></p>
<p>
	<i class="fa fa-location-arrow"></i>
	${fn:replace(COMPANY_OBJ.address,';','<br>')}
</p>
<p>
	<i class="fa fa-phone"></i>: ${fn:replace(COMPANY_OBJ.tel,';','<br>')}
</p>

<p>
	<i class="fa fa-envelope"></i>: <a
		href="mailto:${COMPANY_OBJ.email}">${COMPANY_OBJ.email}</a>
</p>
<p><i class="fa fa-phone-square"></i>: 咨询热线</p>
<p>
	${fn:replace(COMPANY_OBJ.hotlines,';','<br>')}
</p>