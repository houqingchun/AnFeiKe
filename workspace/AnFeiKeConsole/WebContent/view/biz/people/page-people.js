$package('jeecg.people');
jeecg.people = function(){
	var _box = null;
	var _this = {
		config:{
			event:{
				add:function(){
					$('#typeIds_combobox').combobox('reload');
					_box.handler.add();
				},
				edit:function(){
					$('#typeIds_combobox').combobox('reload');
					_box.handler.edit();
				}
			},
  			dataGrid:{
  				title:'用户',
	   			url:'dataList.shtml',
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'account',title:'登陆帐号',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.account;
							}
						},
					{field:'email',title:'邮箱',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.email;
							}
						},
					{field:'pwd',title:'登陆密码',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.pwd;
							}
						},
					{field:'name',title:'昵称',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.name;
							}
						},
					{field:'mobile',title:'移动电话',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.mobile;
							}
						},
					{field:'type',title:'用户类别',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.type;
							}
						},
					{field:'isAvailable',title:'是否可用',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.isAvailable;
							}
						},
					{field:'loginCount',title:'登录总次数',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.loginCount;
							}
						},
					{field:'loginTime',title:'最后登录时间',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.loginTime;
							}
						},
					{field:'isDeleted',title:'是否删除',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.isDeleted;
							}
						},
					{field:'createTime',title:'创建时间',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.createTime;
							}
						},
					{field:'updateTime',title:'修改时间',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.updateTime;
							}
						},
					{field:'createBy',title:'创建人',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.createBy;
							}
						},
					{field:'updateBy',title:'修改人',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.updateBy;
							}
						},
					{field:'isAdmin',title:'是否为管理员',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.isAdmin;
							}
						},
					]]
			}
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	}
	return _this;
}();

$(function(){
	jeecg.people.init();
});