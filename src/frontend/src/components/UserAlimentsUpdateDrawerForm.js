import {Drawer, Col, Form, Row, Button, InputNumber} from 'antd';
import { updateConsumeAliment } from './api';
import { successNotificationWithIcon, errorNotificationWithIcon } from "./Notification";


function UserAlimentsUpdateDrawerForm({showUserAlimentsUpdateDrawer, setShowUserAlimentsUpdateDrawer, userName, alimentName, fetchUserAliments, fetchUserStats}) {

    const onCLose = () => setShowUserAlimentsUpdateDrawer(false);

    const onFinish = value => {
        updateConsumeAliment(userName, alimentName, value.quantity)
            .then(() => {
                successNotificationWithIcon("Consumed Aliment updated", `${value.quantity} portion(s) of ${alimentName} is(are) consumed`);
                fetchUserAliments(userName);
                fetchUserStats(userName);
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
        title="Update consumed Aliment"
        width={720}
        onClose={onCLose}
        visible={showUserAlimentsUpdateDrawer}
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

export default UserAlimentsUpdateDrawerForm;