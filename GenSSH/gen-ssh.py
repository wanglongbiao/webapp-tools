# -*- coding: UTF-8 -*-

import os
import time
import shutil
import xml.etree.ElementTree as et
import re


# 程序的开始
def start():
    dest_project_path = 'D:\\_wang_work\\_code\\'
    default_group_id = 'me.wang'
    default_artifact_id = 'test-webapp1'

    print("\n######## dest_project_path:%s ########\n" % dest_project_path)

    if not os.path.exists(dest_project_path):
        print("%s does not existed, creating..." % dest_project_path)
        os.makedirs(dest_project_path)

    artifact_id = input("artifactId [%s]:" % default_artifact_id)
    if artifact_id == '':
        artifact_id = default_artifact_id
        # print('your artifact_id:' + artifact_id)

    group_id = input("groupId [%s]: " % default_group_id)
    print('your group_id:' + group_id)
    if group_id == '':
        group_id = default_group_id
        print('using your default group_id:' + group_id)

    command = 'mvn archetype:generate -DinteractiveMode=false -DarchetypeArtifactId=maven-archetype-webapp  ' \
              '-DgroupId=' + group_id + ' -DartifactId=' + artifact_id

    os.system(command)

    create_directories(artifact_id, group_id)

    update_pom(artifact_id, group_id)

    copy_files(artifact_id, group_id)

    move_project(artifact_id, dest_project_path)
    add_to_clip(dest_project_path + artifact_id)


# 创建默认的文件夹
def create_directories(artifact_id, group_id):
    web_prefix = './' + artifact_id + '/src/main/webapp/'

    default_package = './' + artifact_id + '/src/main/java/%s' % group_id.replace('.', '/')
    # print("default_package:%s" % default_package)

    os.makedirs(web_prefix + 'common/')
    os.makedirs(web_prefix + 'static/')
    os.makedirs(web_prefix + 'static/js/')
    os.makedirs(web_prefix + 'static/css/')
    os.makedirs(web_prefix + 'static/img/')
    os.makedirs(web_prefix + 'WEB-INF/layouts/')
    os.makedirs(web_prefix + 'WEB-INF/views/')
    os.makedirs(default_package + '/entity')
    os.makedirs(default_package + '/service')
    os.makedirs(default_package + '/repository')
    os.makedirs(default_package + '/controller')
    os.makedirs('./' + artifact_id + '/src/test/java')


# 修改 pom.xml
def update_pom(artifact_id, group_id):
    et.register_namespace('', 'http://maven.apache.org/POM/4.0.0')
    pom_doc = et.parse('./template/pom.xml')
    modify_pom(pom_doc, 'artifactId', artifact_id)
    modify_pom(pom_doc, 'groupId', group_id)
    modify_pom(pom_doc, 'name', artifact_id + ' Maven Webapp')
    modify_pom(pom_doc, 'finalName', artifact_id)
    pom_doc.write('./%s/pom.xml' % artifact_id)


# 复制资源文件到项目中
def copy_files(artifact_id, group_id):
    shutil.copy('./template/web.xml', './' + artifact_id + '/src/main/webapp/WEB-INF/')
    shutil.copy('./template/jsp/taglibs.jsp', './' + artifact_id + '/src/main/webapp/common/')

    shutil.copy('./template/resources/log4j.properties', './' + artifact_id + '/src/main/resources/')
    shutil.copy('./template/resources/jdbc.properties', './' + artifact_id + '/src/main/resources/')
    shutil.copy('./template/resources/dispatcher-servlet.xml', './' + artifact_id + '/src/main/resources/')
    shutil.copy('./template/resources/spring-context.xml', './' + artifact_id + '/src/main/resources/')
    shutil.copy('./template/resources/spring-dao.xml', './' + artifact_id + '/src/main/resources/unused-spring-dao.xml')
    # shutil.copy('./resources/', './' + artifact_id + '/src/main/resources/')


# 将生成的项目目录剪切到指定的目录中去
def move_project(artifact_id, default_path):
    shutil.move('./' + artifact_id, default_path)
    new_path = default_path + artifact_id
    print("new path:" + new_path)
    os.system("start %s" % new_path)


# 获取 xml 文件的命名空间
def get_namespace(element):
    m = re.match('{.*\}', element.tag)
    return m.group(0) if m else ''


# 修改 pom.xml
def modify_pom(doc, ele_name, ele_val):
    # et.register_namespace('', 'http://maven.apache.org/POM/4.0.0')
    # doc = et.parse('./resources/pom.xml')
    root = doc.getroot()

    namespace = get_namespace(root)
    # print('namespace:' + namespace)
    # ai_ele = root.find("artifactId", 'http://maven.apache.org/POM/4.0.0')
    # ai_ele = root.find("artifactId", namespace)
    # print('ok:' + '//{0}groupId'.format(namespace))
    old_element = doc.find(('.//{0}' + ele_name).format(namespace))
    # old_element = doc.find(('{0}build/{0}finalName' + ele_name).format(namespace))
    # ai_ele = doc.findall("project/artifactId")
    old_element.text = ele_val

    # root.remove(ai_ele)
    # ai_ele.text = 'test1'
    # print('ai_ele:' + str(ai_ele))


# 复制文本到剪切板
def add_to_clip(text):
    command = 'echo ' + text.strip() + '| clip'
    print(command)
    os.system(command)


def test_modify_bean():
    print("start")
    et.register_namespace('', 'http://www.springframework.org/schema/beans')
    doc = et.parse('./template/resources/spring-context.xml')
    root = doc.getroot()

    namespace = get_namespace(root)
    # print('namespace:' + namespace)
    # ai_ele = root.find("artifactId", 'http://maven.apache.org/POM/4.0.0')
    # ai_ele = root.find("artifactId", namespace)
    # print('ok:' + '//{0}groupId'.format(namespace))
    old_element = doc.find(('.//{0}' + ele_name).format(namespace))
    # old_element = doc.find(('{0}build/{0}finalName' + ele_name).format(namespace))
    # ai_ele = doc.findall("project/artifactId")
    old_element.text = ele_val
    # modify_pom(pom_doc, 'artifactId', artifact_id)


# 主程序
if __name__ == "__main__":
    start()
    # test_modify_bean()
    input("########## end ##########")
