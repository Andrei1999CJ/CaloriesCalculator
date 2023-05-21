import { Button, Checkbox, Form, Input } from 'antd';
import { signInUser } from './api';
import { successNotificationWithIcon, errorNotificationWithIcon } from "./Notification";
import { useState } from 'react';


 export let email;

function SignInForm({signIn, state, setState}) {


    const onFinish = (values) => {
      signInUser(values)
        .then(() => {
            setState(true);
            email = values.email;
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

    if (signIn) {

    return <Form
               name="basic"
               labelCol={{
                 span: 8,
               }}
               wrapperCol={{
                 span: 16,
               }}
               style={{
                 maxWidth: 600,
               }}
               initialValues={{
                 remember: true,
               }}
               onFinish={onFinish}
               onFinishFailed={onFinishFailed}
               autoComplete="off"
             >
               <Form.Item
                 label="Email"
                 name="email"
                 rules={[
                   {
                     required: true,
                     message: 'Please input your username!',
                   },
                 ]}
               >
                 <Input />
               </Form.Item>

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

               <Form.Item
                 name="remember"
                 valuePropName="checked"
                 wrapperCol={{
                   offset: 8,
                   span: 16,
                 }}
               >
                 <Checkbox>Remember me</Checkbox>
               </Form.Item>

               <Form.Item
                 wrapperCol={{
                   offset: 8,
                   span: 16,
                 }}
               >
                 <Button type="primary" htmlType="submit">
                   Submit
                 </Button>
               </Form.Item>
             </Form>

    }

}

export default SignInForm;