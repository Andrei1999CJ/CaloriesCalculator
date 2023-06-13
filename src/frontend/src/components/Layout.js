import { UserOutlined, LaptopOutlined} from '@ant-design/icons';
import { Layout, Menu, Button, Avatar } from 'antd';
import { useState } from 'react';
import  SecondPage  from './SecondPage';
import  FirstPage  from './FirstPage';
import { email } from './SignInForm';


const { Header, Content, Footer, Sider } = Layout;

function LayoutComp({showLayout}) {
  const [collapsed, setCollapsed] = useState(false);
  const [firstPage, setFirstPage] = useState(true);
  const [secondPage, setSecondPage] = useState(false);

  const TheAvatar = ({name}) => {
      let trim = name.trim();
      if (trim.length === 0) {
          return <Avatar icon={UserOutlined} className="avatar"/>
      }

      const split = trim.split(" ");
      if (split.length === 1) {
          return <Avatar className="avatar">{name.charAt(0).toUpperCase()}</Avatar>
      }

      return <Avatar className="avatar"> {`${name.charAt(0)}${name.charAt(name.length-1)}`} </Avatar>

  }


  if (showLayout) {
  return (
    <Layout
      style={{
        minHeight: '100vh',
      }}
    >
      <Sider collapsible collapsed={collapsed} onCollapse={setCollapsed}>
        <div className="demo-logo-vertical" />
        <Menu theme="dark" defaultSelectedKeys={['2']} mode="inline">
            <Menu.Item key = "1" icon = <TheAvatar name = {email}/>>
                <p style ={{marginLeft: '5%'}}> {email} </p>
            </Menu.Item>
            <Menu.Item key = "2" icon = {<UserOutlined/>}>
                <Button type = "link" style = {{color: "white"}} onClick = {() => {setFirstPage(true); setSecondPage(false);}}> User Info </Button>
            </Menu.Item>
            <Menu.Item key = "3" icon = {<LaptopOutlined />}>
                <Button type = "link" style = {{color: "white"}} onClick = {() => {setFirstPage(false); setSecondPage(true);}}> Aliments </Button>
            </Menu.Item>
        </Menu>
      </Sider>
      <Layout>
        <Header
          style={{
            padding: 0,
          }}
        />
        <Content
          style={{
            margin: '0 16px',
          }}
        >
          <div
            style={{
              padding: 24,
              minHeight: 360,

            }}
          >
             <FirstPage firstPage = {firstPage} email = {email} />
             <SecondPage secondPage = {secondPage} email = {email} />
          </div>
        </Content>
        <Footer
          style={{
            textAlign: 'center',
          }}
        >
          By Cojocariu Andrei
        </Footer>
      </Layout>
    </Layout>
  );
  }
}
export default LayoutComp;