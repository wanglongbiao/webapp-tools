# -*- coding: UTF-8 -*-

import os
import time
import shutil
import xml.etree.ElementTree as et
import re


# import win32clipboard
# from Tkinter import Tk
# import Pyperclip

# 程序的开始，面向过程设计
def start():
    default_path = 'D:\\eclipse_workspace\\'
    print("\n######## default path:%s ########\n" % default_path)

    if not os.path.exists(default_path):
        print("%s does not existed, creating..." % default_path)
        os.mkdir(default_path)
    default_group_id = 'me.wang'
    default_artifact_id = 'test-webapp1'

    artifact_id = input("artifactId [%s]:" % default_artifact_id)
    if artifact_id == '':
        artifact_id = default_artifact_id
        # print('your artifact_id:' + artifact_id)

    group_id = input("groupId [%s]: " % default_group_id)
    # print('your group_id:' + group_id)
    if group_id == '':
        group_id = default_group_id
        # print('using your default group_id:' + group_id)

    command = 'mvn archetype:generate -DinteractiveMode=false -DarchetypeArtifactId=maven-archetype-webapp  ' \
              '-DgroupId=' + group_id + ' -DartifactId=' + artifact_id

    os.system(command)

    create_directories(artifact_id, group_id)

    copy_files(artifact_id, group_id)

    move_project(artifact_id, default_path)


# 创建默认的文件夹
def create_directories(artifact_id, group_id):
    default_package = './' + artifact_id + '/src/main/java/%s' % group_id.replace('.', '/')
    print("default_package:%s" % default_package)

    os.makedirs(default_package + '/entity')
    os.makedirs(default_package + '/service')
    os.makedirs(default_package + '/repository')
    os.makedirs(default_package + '/controller')
    os.makedirs('./' + artifact_id + '/src/test/java')


# 复制资源文件到项目中
def copy_files(artifact_id, group_id):
    et.register_namespace('', 'http://maven.apache.org/POM/4.0.0')
    pom_doc = et.parse('./resources/pom.xml')
    modify_pom(pom_doc, 'artifactId', artifact_id)
    modify_pom(pom_doc, 'groupId', group_id)
    modify_pom(pom_doc, 'name', artifact_id + ' Maven Webapp')
    modify_pom(pom_doc, 'finalName', artifact_id)
    pom_doc.write('./%s/pom.xml' % artifact_id)
    shutil.copy('./resources/web.xml', './' + artifact_id + '/src/main/webapp/WEB-INF/')
    shutil.copy('./resources/log4j.properties', './' + artifact_id + '/src/main/java/resources/')
    shutil.copy('./resources/jdbc.properties', './' + artifact_id + '/src/main/java/resources/')


# 将生成的项目目录剪切到指定的目录中去
def move_project(artifact_id, default_path):
    shutil.move('./' + artifact_id, default_path)
    new_path = default_path + artifact_id
    print("new path:" + new_path)
    os.system("explorer.exe %s" % new_path)


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


# 主程序
if __name__ == "__main__":
    # start()
    # test_modify_xml()
    # start()
    # et.register_namespace('', 'http://maven.apache.org/POM/4.0.0')
    # doc = et.parse('./resources/pom.xml')
    # modify_pom(doc, 'build/finalName', 'xxxxxxx2x')
    # test2()
    # ss = 'default str'
    # print('default..%s' % ss)
    # test1()
    # default_path = 'D:\eclipse_workspace\\'
    # print(default_path)

    # cwd = os.getcwd()
    # print("cwd:" + cwd)
    # os.system("explorer.exe %s" % cwd)
    start()
    input("########## end ##########")
