import { Button, Checkbox, Form, Input, Row, Col, Select } from 'antd';
import { signUp } from './api';
import { successNotificationWithIcon, errorNotificationWithIcon } from "./Notification";


const onFinish = (values) => {
  signUp(values).then(() => {
      successNotificationWithIcon("Account Created", `Account created with success`, 'top');

  }).catch((err) => {
          //var status = err.response.status;
          err.response.json().then((res) => {
                errorNotificationWithIcon("There was an issue", `${res.message} [${err.response.status}] [${res.httpStatus}]`, 'top');
          })
          });
};
const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};

const {Option} = Select;



function SignUpForm({signUp}) {

    if (signUp) {

    return <Form
               name="basic"
               labelCol={{
                 span: 20,
               }}
               wrapperCol={{
                 span: 20,
               }}
               style={{
                 maxWidth: 1200,
               }}
               initialValues={{
                 remember: true,
               }}
               onFinish={onFinish}
               onFinishFailed={onFinishFailed}
               autoComplete="off"
               layout = "vertical"
             >
             <Row  gutter={16}>
             <Col>
               <Form.Item
                 label="First Name"
                 name="firstName"
                 rules={[
                   {
                     required: true,
                     message: 'Please input your first name!',
                   },
                 ]}
               >
                 <Input />
               </Form.Item>
               </Col>
               <Col>
               <Form.Item
                    label="Last Name"
                    name="lastName"
                    rules={[
                    {
                     required: true,
                    message: 'Please input your first name!',
                  },
                 ]}
                  >
                  <Input />

               </Form.Item>
               </Col>
               </Row>
                <Row gutter={16}>
                <Col>
                <Form.Item
                                    label="Email"
                                    name="email"
                                    rules={[
                                    {
                                     required: true,
                                    message: 'Please input your first name!',
                                  },
                                 ]}
                                  >
                                  <Input />

                               </Form.Item>
               </Col>
               <Col>
               <Form.Item
                 label="Password"
                 name="password"
                 rules={[
                   {
                     required: true,
                     message: 'Please input your password!',
                   },
                 ]}
               >
                 <Input.Password />
               </Form.Item>
               </Col>
               </Row>
               <Row >
               <Col span = {8}>
               <Form.Item
                 label="Gender"
                 name="gender"
                 rules={[
                   {
                     required: true,
                     message: 'Please input your first name!',
                   },
                 ]}
               >
               <Select>
                    <Option value = "MALE"> MALE</Option>
                    <Option value = "FEMALE"> FEMALE</Option>
               </Select>
               </Form.Item>
               </Col>
               </Row>


               <Form.Item

               >
                 <Button type="primary" htmlType="submit">
                   Submit
                 </Button>
               </Form.Item>
             </Form>;
    }

}

export default SignUpForm;