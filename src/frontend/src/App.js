import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import { Button, Space } from 'antd';
import LayoutComp from './components/Layout';
import AccountPage from './components/AccountPage';



function App() {

    const [state, setState] = useState(false);
    const [showLayout, setShowLayout] = useState(false);

    const SendButton =  () => {
        if (!state) {
        return <AccountPage state = {state} setState = {setState}/>;
        }

    }


    useEffect(() => {
         if (state) {
            console.log("buton apasat");
            setShowLayout(true);
         }
    }, [state])

   return <>
   <SendButton/>
   <LayoutComp showLayout = {showLayout} />
   </>

}

export default App;
