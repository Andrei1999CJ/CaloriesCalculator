import { Space, Table, Tag, Button, Progress, Radio } from 'antd';
import { useState, useEffect } from 'react';
import {  getStats, getUserAliments, deleteAllUserAliments, deleteUserAliment } from './api.js';
import { PlusOutlined, MinusOutlined } from '@ant-design/icons';
import { successNotificationWithIcon, errorNotificationWithIcon } from "./Notification";
import UserAlimentsUpdateDrawerForm from "./UserAlimentsUpdateDrawerForm";






function FirstPage({firstPage, email}) {
    const [stats, setStats] = useState(false);
    const [userAliments, setUserAliments] = useState(false);
    const [showUserAlimentsUpdateDrawer, setShowUserAlimentsUpdateDrawer] = useState(false);
    const [alimentName, setAlimentName] = useState();

    const fetchStats = (email) =>
            getStats(email)
                .then(res => res.json())
                .then(data => {
                    console.log(data);
                    setStats(data);
                }).catch((err) => {
                                                //var status = err.response.status;
                                                err.response.json().then((res) => {
                                                      errorNotificationWithIcon("There was an issue", `${res.message} [${err.response.status}] [${res.httpStatus}]`);
                                                })
                                                });



    const fetchUserAliments = (email) => {
        getUserAliments(email)
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setUserAliments(data);
            }).catch((err) => {
                                            //var status = err.response.status;
                                            err.response.json().then((res) => {
                                                  errorNotificationWithIcon("There was an issue", `${res.message} [${err.response.status}] [${res.httpStatus}]`);
                                            })
                                            });


    }

    const deleteUserAliments = (email) => {
        deleteAllUserAliments(email)
           .then(() => {
              successNotificationWithIcon("Aliments deleted", `All consumed aliments are deleted`);
              fetchUserAliments(email);
              fetchStats(email);

            }).catch((err) => {
                              //var status = err.response.status;
                              err.response.json().then((res) => {
                                    errorNotificationWithIcon("There was an issue", `${res.message} [${err.response.status}] [${res.httpStatus}]`);
                              })
                              });



    }

    const removeUserAliment = (email, alimentName) => {
            deleteUserAliment(email, alimentName)
                .then(() => {
                    successNotificationWithIcon("Aliment deleted", `Aliment with name ${alimentName} is deleted`);
                    fetchUserAliments(email);
                    fetchStats(email);

                }).catch((err) => {
                                                //var status = err.response.status;
                                                err.response.json().then((res) => {
                                                      errorNotificationWithIcon("There was an issue", `${res.message} [${err.response.status}] [${res.httpStatus}]`);
                                                })
                                                });



    }

    const columns = [
      {
        title: 'Name',
        dataIndex: 'alimentName',
        key: 'name',
      },
      {
        title: 'Quantity',
        dataIndex: 'quantity',
        key: 'quantity',
      },
      {
          title: 'Actions',
          dataIndex: 'actions',
          key: 'actions',
          render: (text, aliment) => <Radio.Group>
                                             <Radio.Button onClick = {() => {setAlimentName(aliment.alimentName); setShowUserAlimentsUpdateDrawer(!showUserAlimentsUpdateDrawer)}} >Add more</Radio.Button>
                                             <Radio.Button onClick = {() => removeUserAliment(email, aliment.alimentName)}> Remove </Radio.Button>
                                     </Radio.Group>
        },
    ];


    useEffect(() => {
           if (firstPage) {
            console.log("component mounted");
             fetchStats(email);
             fetchUserAliments(email);
             }
           },[firstPage])

   if (firstPage) {
           return <>
           <UserAlimentsUpdateDrawerForm showUserAlimentsUpdateDrawer = {showUserAlimentsUpdateDrawer}
           setShowUserAlimentsUpdateDrawer = {setShowUserAlimentsUpdateDrawer}
           userName = {email} alimentName = {alimentName}
            fetchUserAliments = {fetchUserAliments} fetchUserStats = {fetchStats}/>
           <Table dataSource = {userAliments} columns = {columns} bordered title= {() => <div><Button  onClick={() => {deleteUserAliments(email);}} type="primary" shape="round" icon = {<MinusOutlined />}  size="small">
                  Remove All Consumed Aliments
                  </Button> <center>Consumed Aliments</center></div>} rowKey={aliment => aliment.alimentName}  />
           <center>
               <Progress type="circle" percent={stats.calories * 100 / 2000} strokeColor = 'purple' format={() => `${stats.calories} Calories`}  />
               <Progress type="circle" percent={stats.protein} strokeColor = 'red' format={(percent) => `${stats.protein} Protein`} style = {{marginLeft: '2%'}} />
               <Progress type="circle" percent={stats.carbs} strokeColor = 'yellow' format={(percent) => `${stats.carbs} Carbs`} style = {{marginLeft: '2%'}} />
               <Progress type="circle" percent={stats.fat} strokeColor = 'blue' format={(percent) => `${stats.fat} Fat`} style = {{marginLeft: '2%'}} />
               <Progress type="circle" percent={stats.fiber} strokeColor = 'green' format={(percent) => `${stats.fiber} Fiber`} style = {{marginLeft: '2%'}} />
           </center>

           </>



       }




}

export default FirstPage;