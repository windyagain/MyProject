<style scoped>
    .layout{
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: hidden;
    }
    .layout-header-bar{
        background: #fff;
        box-shadow: 0 1px 1px rgba(0,0,0,.1);
    }
    .layout-logo-left{
        width: 90%;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        margin: 15px auto;
    }
    .menu-icon{
        transition: all .3s;
    }
    .rotate-icon{
        transform: rotate(-90deg);
    }
    .menu-item span{
        display: inline-block;
        overflow: hidden;
        width: 69px;
        text-overflow: ellipsis;
        white-space: nowrap;
        vertical-align: bottom;
        transition: width .2s ease .2s;
    }
    .menu-item i{
        transform: translateX(0px);
        transition: font-size .2s ease, transform .2s ease;
        vertical-align: middle;
        font-size: 16px;
    }
    .collapsed-menu span{
        width: 0px;
        transition: width .2s ease;
    }
    .collapsed-menu i{
        transform: translateX(5px);
        transition: font-size .2s ease .2s, transform .2s ease .2s;
        vertical-align: middle;
        font-size: 22px;
    }
</style>
<template>
    <div class="layout">
        <Layout>
            <div >
                 <Menu active-name="1-2" theme="dark" width="auto" :class="menuitemClasses">
                  				<div class="col-md-2" style="display:inline-block;">
                <div class="panel-group table-responsive" role="tablist">
                    <div class="panel leftMenu">
                        <!-- 利用data-target指定要折叠的分组列表 -->
                        <div class="panel-heading" id="collapseListGroupHeading1" data-toggle="collapse" data-target="#collapseListGroup1" role="tab" >
                            <h4 class="panel-title">
                                分组1
                                <span class="glyphicon glyphicon-chevron-up right"></span>
                            </h4>
                        </div>
                        <!-- .panel-collapse和.collapse标明折叠元素 .in表示要显示出来 -->
                        <div id="collapseListGroup1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="collapseListGroupHeading1">
                            <ul class="list-group">
                              <li class="list-group-item">
                                <!-- 利用data-target指定URL -->
                                <a class="menu-item-left">
                                    分组项1-1
                                </a>
                              </li>
                              <li class="list-group-item">
                                <a class="menu-item-left">
                                    分组项1-2
                                </a>
                              </li>
                            </ul>
                        </div>
                    </div><!--panel end-->
                    <div class="panel leftMenu">
                        <div class="panel-heading" id="collapseListGroupHeading2" data-toggle="collapse" data-target="#collapseListGroup2" role="tab" >
                            <h4 class="panel-title">
                                分组2
                                <span class="glyphicon glyphicon-chevron-down right"></span>
                            </h4>
                        </div>
                        <div id="collapseListGroup2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapseListGroupHeading2">
                            <ul class="list-group">
                              <li class="list-group-item">
                                <a class="menu-item-left">
                                        分组项2-1
                                </a>
                              </li>
                              <li class="list-group-item">
                                <a class="menu-item-left">
                                   分组项2-2
                                </a>
                              </li>
                            </ul>
                        </div>
                    </div>  
                </div>
            </div>
                </Menu> 

            </div>
            <Layout>
                <Header :style="{padding: 0}" class="layout-header-bar">
                    <Icon @click.native="collapsedSider" :class="rotateIcon" :style="{margin: '0 20px'}" type="md-menu" size="24"></Icon>
                </Header>
                <Content :style="{margin: '20px', background: '#fff', minHeight: '260px'}">
                    Content
                </Content>
            </Layout>
        </Layout>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                isCollapsed: false
            }
        },
        computed: {
            rotateIcon () {
                return [
                    'menu-icon',
                    this.isCollapsed ? 'rotate-icon' : ''
                ];
            },
            menuitemClasses () {
                return [
                    'menu-item',
                    this.isCollapsed ? 'collapsed-menu' : ''
                ]
            }
        },
        methods: {
            collapsedSider () {
                this.$refs.side1.toggleCollapse();
            }
        }
    }
</script>
<style>
 .panel-group{max-height:770px;overflow: auto;}
            .leftMenu{margin:0px 0px;color:#000;}
            .leftMenu .panel-heading{font-size:14px;padding-left:20px;height:36px;line-height:36px;position:relative;cursor:pointer;border-radius:0px;}/*转成手形图标*/
            .leftMenu .panel-heading span{position:absolute;right:10px;top:12px; }
            .leftMenu .menu-item-left{padding: 5px 10px; text-decoration: none; color:#000;}
            .leftMenu .list-group-item:hover{color:#fff;cursor:pointer;background: #e7e7e7;}
            .glyphicon{font-size: 9px;}
</style>
