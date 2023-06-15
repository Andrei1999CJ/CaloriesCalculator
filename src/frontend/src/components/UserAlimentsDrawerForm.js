import {Drawer, Col, Select, Form, Row, Button, InputNumber} from 'antd';
import { consumeAliment } from './api';
import { useState } from 'react';
import { successNotificationWithIcon, errorNotificationWithIcon } from "./Notification";


function UserAlimentsDrawerForm({showUserAlimentsDrawer, setShowUserAlimentsDrawer, userName, alimentName}) {

    const onCLose = () => setShowUserAlimentsDrawer(false);

    const onFinish = value => {
        consumeAliment(userName, alimentName, value.quantity)
            .then(() => {
                successNotificationWithIcon("Consumed Aliment added", `${value.quantity} portion(s) of ${alimentName} is(are) consumed`);
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
        title="Consume Aliment"
        width={720}
        onClose={onCLose}
        visible={showUserAlimentsDrawer}
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
                        name="quantity"
                        label="Quantity"
                        rules={[{required: true, message: 'Please enter quantity'}]}
                    >
                        <InputNumber placeholder="Please enter quantity"/>
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

export default UserAlimentsDrawerForm;