$package('jeecg.slide');
jeecg.slide = function(){
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
  				title:'幻灯片信息',
	   			url:'dataList.do',
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'slideType',title:'幻灯片类别',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.slideType;
							}
						},
					{field:'themeTitle',title:'标题',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.themeTitle;
							}
						},
					{field:'photoPath',title:'图片',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.photoPath;
							}
						},
					{field:'themeDesc',title:'主题内容',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.themeDesc;
							}
						},
					{field:'themeComments',title:'注释',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.themeComments;
							}
						},
					{field:'displayOrder',title:'显示顺序',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.displayOrder;
							}
						},
					{field:'createBy',title:'创建人',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.createBy;
							}
						},
					{field:'createTime',title:'创建时间',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.createTime;
							}
						},
					{field:'updateBy',title:'更新人',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.updateBy;
							}
						},
					{field:'updateTime',title:'修改时间',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.updateTime;
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
	jeecg.slide.init();
});