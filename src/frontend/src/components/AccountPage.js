import { Layout, Menu, Button } from 'antd';
import { useState } from 'react';
import SignInForm from './SignInForm';
import SignUpForm from './SignUpForm';


const { Header, Content } = Layout;

function AccountPage({state, setState}) {

      const [signUp, setSignUp] = useState(false);
      const [signIn, setSignIn] = useState(true);

      return <Layout className="layout">
          <Header
            style={{
              display: 'flex',
              alignItems: 'center',
            }}
          >
            <div className="demo-logo" />
            <Menu
              theme="dark"
              mode="horizontal"
              defaultSelectedKeys={['1']}
              style={{position:"absolute", right: "0px"}}
            >
            <Menu.Item key = "1" >
                <Button type = 'link' style = {{color: 'white'}} onClick = {() => {setSignUp(false); setSignIn(true);}}> SignIn </Button>
            </Menu.Item>
            <Menu.Item key = "2" >
                <Button type = 'link' style = {{color: 'white'}} onClick = {() => {setSignUp(true); setSignIn(false);}}> SignUp </Button>
            </Menu.Item>

            </Menu>
          </Header>
          <Content
            style={{
              position: "absolute",
              right: "25%",
              left: "25%",
              top: "30%"
            }}
          >
            <div
              className="site-layout-content"

            >
              <SignUpForm signUp = {signUp}/>
              <SignInForm signIn = {signIn} state = {state} setState = {setState} />
            </div>
          </Content>
        </Layout>;


}
export default AccountPage;