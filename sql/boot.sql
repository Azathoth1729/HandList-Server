insert into space_node(nodetype, path)
values ('Folder', '/软件开发'),

       ('Folder', '/软件开发/前端设计'),
       ('List', '/软件开发/前端设计/UI设计'),
       ('List', '/软件开发/前端设计/导航设计'),

       ('Folder', '/软件开发/后端设计'),
       ('List', '/软件开发/后端设计/数据库设计'),
       ('List', '/软件开发/后端设计/api设计'),
       ('List', '/软件开发/后端设计/性能优化'),

       ('List', '/软件开发/Bug追踪'),
       ('List', '/软件开发/测试')
;

CREATE OR REPLACE FUNCTION random_between(low INT, high INT)
    RETURNS INT AS
$$
BEGIN
    RETURN floor(random() * (high - low + 1) + low);
END;
$$ language 'plpgsql' STRICT;

insert into task(task_name, description, create_time, start_time, end_time, status, fk_node_id)
VALUES ('主界面设计',
        '确定主界面的整体布局，包括各个元素的位置、大小和相对关系。考虑到用户体验和易用性，设计一个清晰、直观的布局。标题和标识：确定主界面的标题和标识，使用户能够快速识别出应用程序或系统的名称和功能。',
        current_date, current_date + random_between(-15, 15), start_time + random_between(0, 30), 'Todo',
        (select id from space_node where path = '/软件开发/前端设计/UI设计')),
       ('用户中心设计',
        '用户登录和注册：设计用户中心的登录和注册界面，包括输入用户名、密码和其他必要的信息，以及相关的验证和错误提示。用户信息管理：提供用户管理个人信息的功能，包括修改用户名、密码、头像、联系方式等个人资料，以及查看和编辑其他相关信息。',
        current_date, current_date + random_between(-15, 15), start_time + random_between(0, 30), 'Done',
        (select id from space_node where path = '/软件开发/前端设计/UI设计')),
       ('UI美化',
        'UI美化任务内容主要包括设计和优化界面的视觉效果，以提升用户体验和吸引力。这包括选择合适的配色方案、字体和图标，调整元素的大小和布局，优化界面的交互动效，以及确保界面在不同设备上的可适应性和一致性。',
        current_date, current_date + random_between(-15, 15), start_time + random_between(0, 30), 'InProgress',
        (select id from space_node where path = '/软件开发/前端设计/UI设计')),

       ('结构设计',
        '确定导航栏的整体结构，包括主导航、子导航、下拉菜单等，以及它们之间的层次和关系。',
        current_date, current_date + random_between(-15, 15), start_time + random_between(0, 30), 'Todo',
        (select id from space_node where path = '/软件开发/前端设计/导航设计')),
       ('布局设计',
        '确定导航栏在页面中的位置和样式，考虑导航栏的固定或浮动，以及与其他页面元素的协调和交互。',
        current_date, current_date + random_between(-15, 15), start_time + random_between(0, 30), 'Done',
        (select id from space_node where path = '/软件开发/前端设计/导航设计')),
       ('链接设计',
        '确定导航栏中的链接文本、图标或按钮，以及它们的样式和交互效果，使用户能够清晰地理解并点击导航选项。',
        current_date, current_date + random_between(-15, 15), start_time + random_between(0, 30), 'InProgress',
        (select id from space_node where path = '/软件开发/前端设计/导航设计')),
       ('动画设计',
        '添加适当的动效和过渡效果，使导航在用户操作时具有流畅、自然的交互体验，如下拉菜单的展开收起、切换页面时的过渡效果等。',
        current_date, current_date + random_between(-15, 15), start_time + random_between(0, 30), 'Todo',
        (select id from space_node where path = '/软件开发/前端设计/导航设计'))
;
;

update task
set description = '确定主界面的整体布局，包括各个元素的位置、大小和相对关系。考虑到用户体验和易用性，设计一个清晰、直观的布局。标题和标识：确定主界面的标题和标识，使用户能够快速识别出应用程序或系统的名称和功能。',
    start_time  = current_date + random_between(-15, 15),
    end_time    = start_time + random_between(0, 30)
where task_name = '主界面设计'
;



select current_date + random_between(-15, 15);