package biz.controller.people;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import biz.entity.people.People;
import biz.page.people.PeoplePage;
import biz.service.people.PeopleService;

import com.base.util.DataValidator;
import com.base.web.BaseValidator;

public class PeopleValidator extends BaseValidator {

	@Autowired(required=false)
	private PeopleService<People> peopleService; 
	private People people;
	private int mode; //1-注册，2-修改，3-删除，4-修改密码，5-找回密码-验证,6-找回密码-提交 7-登录
	
	public PeopleValidator(People people, int mode, PeopleService<People> peopleService) {
		this.people = people;
		this.mode = mode;
		this.peopleService = peopleService;
	}
	
	/**
	 * 验证帐号合法性
	 * @param people
	 * @param response
	 * @throws Exception
	 */
	private HashMap<String, Object[]> validateAccount() throws Exception{
		HashMap<String, Object[]> errors = new HashMap<String, Object[]>();
		//检查帐号唯一性
		PeoplePage page = new PeoplePage();
		page.setId(people.getId());
		if (!StringUtils.isBlank(people.getAccount())){
			page = new PeoplePage();
			page.setAccount(people.getAccount());
			if (peopleService.queryUniqueObjCount(page) > 0){
				errors.put("account", new Object[] { "errors.field.already.used", "帐号"});
			}
		}
		if (!StringUtils.isBlank(people.getEmail())){
			page = new PeoplePage();
			page.setEmail(people.getEmail());
			if (peopleService.queryUniqueObjCount(page) > 0){
				errors.put("email", new Object[] { "errors.field.already.used", "邮箱"});
			}
		}
		if (!StringUtils.isBlank(people.getName())){
			page = new PeoplePage();
			page.setName(people.getName());
			if (peopleService.queryUniqueObjCount(page) > 0){
				errors.put("name", new Object[] { "errors.field.already.used", "昵称"});
			}
		}
		if (!StringUtils.isBlank(people.getMobile())){
			page = new PeoplePage();
			page.setMobile(people.getMobile());
			if (peopleService.queryUniqueObjCount(page) > 0){
				errors.put("mobile", new Object[] { "errors.field.already.used", "电话"});
			}
		}
		
		return errors;
	}

	public Map<String, Object[]> validate() throws Exception{
		HashMap<String, Object[]> errors = new HashMap<String, Object[]>();

		if (mode == 1){
			//注册
			if (DataValidator.isBlankOrNull(people.getEmail())) {
				errors.put("email", new Object[] { "errors.field.required", "邮箱"});
			}
			
			if (DataValidator.isBlankOrNull(people.getAccount())) {
				errors.put("account", new Object[] { "errors.field.required", "帐号"});
			}
			
			if (DataValidator.isBlankOrNull(people.getName())) {
				errors.put("name", new Object[] { "errors.field.required", "昵称"});
			}
			
			if (DataValidator.isBlankOrNull(people.getPwd())) {
				errors.put("pwd", new Object[] { "errors.field.required", "密码"});
			}
			
			if (!DataValidator.isBlankOrNull(people.getPwd()) && !people.getPwd().equals(people.getPwdRepeat())) {
				errors.put("pwdRepeat", new Object[] { "errors.password.not.match", "密码"});
			}
			
			if (errors.isEmpty()){
				errors.putAll(this.validateAccount());
			}
		}else if (mode == 2){
			//修改
			if (DataValidator.isBlankOrNull(people.getName())) {
				errors.put("name", new Object[] { "errors.field.required", "昵称"});
			}else{
				PeoplePage page = new PeoplePage();
				page.setId(people.getId());
				page.setName(people.getName());
				if (peopleService.queryUniqueObjCount(page) > 0){
					errors.put("name", new Object[] { "errors.field.already.used", "昵称"});
				}
			}
		}else if (mode == 3){
			//删除
		}else if (mode == 4){
			//修改密码
			if (DataValidator.isBlankOrNull(people.getOldPwd())) {
				errors.put("oldPwd", new Object[] { "errors.field.required", "旧密码"});
			}
			
			if (DataValidator.isBlankOrNull(people.getPwd())) {
				errors.put("pwd", new Object[] { "errors.field.required", "密码"});
			}
			
			if (!DataValidator.isBlankOrNull(people.getPwd()) && !people.getPwd().equals(people.getPwdRepeat())) {
				errors.put("pwdRepeat", new Object[] { "errors.password.not.match", "密码"});
			}
		}else if (mode == 5){
			//找回密码-验证
			if (DataValidator.isBlankOrNull(people.getEmail())) {
				errors.put("email", new Object[] { "errors.field.required", "邮箱"});
			}
			
			if (DataValidator.isBlankOrNull(people.getAccount())) {
				errors.put("account", new Object[] { "errors.field.required", "帐号"});
			}
			
			if (DataValidator.isBlankOrNull(people.getName())) {
				errors.put("name", new Object[] { "errors.field.required", "昵称"});
			}
		}else if (mode == 6){
			//找回密码-提交
			if (DataValidator.isBlankOrNull(people.getId()) || this.peopleService.queryById(people.getId()) == null) {
				errors.put("account", new Object[] { "errors.field.not.exist", "帐号"});
			}
			
			if (DataValidator.isBlankOrNull(people.getPwd())) {
				errors.put("pwd", new Object[] { "errors.field.required", "密码"});
			}
			
			if (!DataValidator.isBlankOrNull(people.getPwd()) && !people.getPwd().equals(people.getPwdRepeat())) {
				errors.put("pwdRepeat", new Object[] { "errors.password.not.match", "密码"});
			}			
		}
		
//		for (int i = 0; i < basepeople.getColumns().length; i++) {
//			FieldDefn field = new FieldDefn(basepeople.getColumns()[i]);
//			if (field.isRequired()) {
//				if (DataValidator.isBlankOrNull(basepeople.getValueAt(field.getFieldName()).toString())) {
//					errors.put(field.getFieldName(), new Object[] { "errors.field.required", field.getDescr() });
//				}
//			}
//		}

		return errors;
	}

	public PeopleService<People> getPeopleService() {
		return peopleService;
	}

	public void setPeopleService(PeopleService<People> peopleService) {
		this.peopleService = peopleService;
	}
}
