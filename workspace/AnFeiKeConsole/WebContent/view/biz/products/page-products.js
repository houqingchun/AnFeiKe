$package('jeecg.products');
jeecg.products = function(){
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
  				title:'机型',
	   			url:'dataList.do',
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'name',title:'机型型号',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.name;
							}
						},
					{field:'types',title:'类别',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.types;
							}
						},
					{field:'brand',title:'品牌',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.brand;
							}
						},
					{field:'seatsNo',title:'座位数',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.seatsNo;
							}
						},
					{field:'ranges',title:'续航里程',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.ranges;
							}
						},
					{field:'rangesUnit',title:'里程单位',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.rangesUnit;
							}
						},
					{field:'speeds',title:'速度',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.speeds;
							}
						},
					{field:'speedsUnit',title:'速度单位',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.speedsUnit;
							}
						},
					{field:'loads',title:'最大载重',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.loads;
							}
						},
					{field:'loadsUnit',title:'最大载重单位',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.loadsUnit;
							}
						},
					{field:'photoPath',title:'照片',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.photoPath;
							}
						},
					{field:'shortDesc',title:'简述',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.shortDesc;
							}
						},
					{field:'description',title:'详细描述',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.description;
							}
						},
					{field:'manufacture',title:'制造商',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.manufacture;
							}
						},
					{field:'produceDate',title:'生产日期',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.produceDate;
							}
						},
					{field:'price',title:'参考价',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.price;
							}
						},
					{field:'priceUnit',title:'参考价单位',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.priceUnit;
							}
						},
					{field:'prePay',title:'预付价',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.prePay;
							}
						},
					{field:'prePayUnit',title:'预付价单位',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.prePayUnit;
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
	jeecg.products.init();
});