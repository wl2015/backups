function showDemo(){
	var demoHtml= '        <div class="demo-left">'
		+ '        <div class="demo-left-font">'
		+ '            <div class="font-area">'
		+ '                <span class="font1">行业精美'
		+ '                    <span class="font2">模板</span>'
		+ '                </span>'
		+ '            </div>'
		+ '        </div>'
		+ '    </div>'
		+ '    '
		+ '    <div class="demo-area">'
		+ '        <div class="demo-area-content">'
		+ '            <section class="demo-block-content">'
		+ '                <div class="demo-block">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/1.jpg"></img>'
		+ '                    <span>心灵-感悟</span>'
		+ '                    <div class="demo-block-cover">'
		+ '                        <img src="'+$.WEB_ROOT+'/res/img/test/1_1.png"></img>'
		+ '                        <div class="btn-content">'
		+ '                            <span class="btn-add">添加</span>'
		+ '                            <span class="btn-preview">预览</span>'
		+ '                        </div>'
		+ '                    </div>'
		+ '                </div>'
		+ '            </section>'
		+ '            <section class="demo-block-content">'
		+ '                <div class="demo-block">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/2.jpg"></img>'
		+ '                    <span>电商-妇女节-推广</span>'
		+ '                    <div class="demo-block-cover">'
		+ '                        <img src="'+$.WEB_ROOT+'/res/img/test/2_2.png"></img>'
		+ '                        <div class="btn-content">'
		+ '                            <span class="btn-add">添加</span>'
		+ '                            <span class="btn-preview">预览</span>'
		+ '                        </div>'
		+ '                    </div>'
		+ '                </div>'
		+ '            </section>'
		+ '            <section class="demo-block-content">'
		+ '                <div class="demo-block">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/3.jpg"></img>'
		+ '                    <span>招聘-扁平化卡通1</span>'
		+ '                    <div class="demo-block-cover">'
		+ '                        <img src="'+$.WEB_ROOT+'/res/img/test/3_3.png"></img>'
		+ '                        <div class="btn-content">'
		+ '                            <span class="btn-add">添加</span>'
		+ '                            <span class="btn-preview">预览</span>'
		+ '                        </div>'
		+ '                    </div>'
		+ '                </div>'
		+ '            </section>'
		+ '            <section class="demo-block-content">'
		+ '                <div class="demo-block">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/4.jpg"></img>'
		+ '                    <span>招聘-扁平化卡通2</span>'
		+ '                    <div class="demo-block-cover">'
		+ '                        <img src="'+$.WEB_ROOT+'/res/img/test/4_4.png"></img>'
		+ '                        <div class="btn-content">'
		+ '                            <span class="btn-add">添加</span>'
		+ '                            <span class="btn-preview">预览</span>'
		+ '                        </div>'
		+ '                    </div>'
		+ '                </div>'
		+ '            </section>'
		+ '            <section class="demo-block-content">'
		+ '                <div class="demo-block">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/5.jpg"></img>'
		+ '                    <span>情人节-温情</span>'
		+ '                    <div class="demo-block-cover">'
		+ '                        <img src="'+$.WEB_ROOT+'/res/img/test/5_5.png"></img>'
		+ '                        <div class="btn-content">'
		+ '                            <span class="btn-add">添加</span>'
		+ '                            <span class="btn-preview">预览</span>'
		+ '                        </div>'
		+ '                    </div>'
		+ '                </div>'
		+ '            </section>'
		+ '        </div>'
		+ '    </div>';
	$('#common-content').html(demoHtml);
}