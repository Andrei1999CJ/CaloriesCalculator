import {Drawer, Input, Col, Select, Form, Row, Button, InputNumber} from 'antd';
import { addAliment } from './api';
import { useState } from 'react';
import { successNotificationWithIcon, errorNotificationWithIcon } from "./Notification";


function AlimentsDrawerForm({showAlimentsDrawer, setShowAlimentsDrawer, fetchAllAliments}) {

    const onCLose = () => setShowAlimentsDrawer(false);

    const onFinish = values => {
        addAliment(values).then(() => {
                successNotificationWithIcon("Aliment added", `Aliment with name ${values.name} is added`);
                console.log("gata");
                fetchAllAliments();
        }).catch((err) => {
                                                      //var status = err.response.status;
                                                      err.response.json().then((res) => {
                                                            errorNotificationWithIcon("There was an issue", `${res.message} [${err.response.status}] [${res.httpStatus}]`);
                                                      })
                                                      });
        onCLose();
    };

    const onFinishFailed = errorInfo => {
        alert(JSON.stringify(errorInfo, null, 2));
    };

    return <Drawer
        title="Add new Aliment"
        width={720}
        onClose={onCLose}
        visible={showAlimentsDrawer}
        bodyStyle={{paddingBottom: 80}}
        footer={
            <div
                style={{
                    textAlign: 'right',
                }}
            >
                <Button onClick={onCLose} style={{marginRight: 8}}>
                    Cancel
                </Button>
            </div>
        }
    >
        <Form layout="vertical"
              onFinishFailed={onFinishFailed}
              onFinish={onFinish}
              hideRequiredMark>
            <Row gutter={16}>
                <Col span={12}>
                    <Form.Item
                        name="name"
                        label="Name"
                        rules={[{required: true, message: 'Please enter aliment name'}]}
                    >
                        <Input placeholder="Please enter aliment name"/>
                    </Form.Item>
                </Col>
                <Col span={12}>
                    <Form.Item
                        name="calories"
                        label="Calories"
                        rules={[{required: true, message: 'Please enter aliment calories'}]}
                    >
                        <InputNumber placeholder="Please enter aliment calories"/>
                    </Form.Item>
                </Col>
            </Row>
            <Row gutter={16}>
                <Col span={12}>
                    <Form.Item
                        name="protein"
                        label="Protein"
                        rules={[{required: true, message: 'Please enter aliment protein'}]}
                    >
                    <InputNumber placeholder="Please enter aliment protein"/>
                    </Form.Item>
                </Col>
                 <Col span={12}>
                    <Form.Item
                        name="carbs"
                        label="Carbs"
                        rules={[{required: true, message: 'Please enter aliment carbs'}]}
                        >
                        <InputNumber placeholder="Please enter aliment carbs"/>
                    </Form.Item>
                 </Col>
            </Row>
            <Row gutter={16}>
                            <Col span={12}>
                                <Form.Item
                                    name="fat"
                                    label="Fat"
                                    rules={[{required: true, message: 'Please enter aliment fat', }]}
                                >
                                <InputNumber placeholder="Please enter aliment fat"/>
                                </Form.Item>
                            </Col>
                             <Col span={12}>
                                <Form.Item
                                    name="fiber"
                                    label="Fiber"
                                    rules={[{required: true, message: 'Please enter aliment fiber'}]}
                                    >
                                    <InputNumber placeholder="Please enter aliment fiber"/>
                                </Form.Item>
                             </Col>
                        </Row>
            <Row>
                <Col span={12}>
                    <Form.Item >
                        <Button type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Col>
            </Row>
        </Form>
    </Drawer>
}

export default AlimentsDrawerForm;