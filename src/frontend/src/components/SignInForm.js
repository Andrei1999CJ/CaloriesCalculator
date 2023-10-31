import { Button, Checkbox, Form, Input } from 'antd';
import { signInUser } from './api';
import { errorNotificationWithIcon } from "./Notification";


 export let email;
 export let token;

function SignInForm({signIn, state, setState}) {


    const onFinish = (values) => {
      signInUser(values)
        .then(res => res.json())
        .then(res => {
            setState(true);
            email = values.email;
            token = res.token;
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