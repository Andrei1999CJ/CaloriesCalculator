import { Table, Tag, Button, Radio } from 'antd';
import { useState, useEffect } from 'react';
import { getAllAliments, deleteAliment } from './api.js';
import { PlusOutlined } from '@ant-design/icons';
import AlimentsDrawerForm from './AlimentsDrawerForm';
import UserAlimentsDrawerForm from './UserAlimentsDrawerForm';
import { successNotificationWithIcon, errorNotificationWithIcon } from "./Notification";






function SecondPage ({secondPage, email}) {
    const [aliments, setAliments] = useState([]);
    const [showAlimentsDrawer, setShowAlimentsDrawer] = useState(false);
    const [showUserAlimentsDrawer, setShowUserAlimentsDrawer] = useState(false);
    const [alimentName, setAlimentName] = useState();

    const removeAliment = (alimentName) =>
            deleteAliment(alimentName)
                .then(() => {
                    successNotificationWithIcon("Aliment deleted", `Aliment with name ${alimentName} is deleted`);
                    fetchAllAliments();

                }).catch((err) => {
                                                              //var status = err.response.status;
                                                              err.response.json().then((res) => {
                                                                    errorNotificationWithIcon("There was an issue", `${res.message} [${err.response.status}] [${res.httpStatus}]`);
                                                              })
                                                              });

    const columns = [
      {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
      },
      {
        title: 'Calories',
        dataIndex: 'calories',
        key: 'calories',
      },
      {
        title: 'Protein',
        dataIndex: 'protein',
        key: 'protein',
      },
      {
          title: 'Carbs',
          dataIndex: 'carbs',
          key: 'carbs',
        },
        {
          title: 'Fat',
          dataIndex: 'fat',
          key: 'fat',
        },
        {
          title: 'Fiber',
          dataIndex: 'fiber',
          key: 'fiber',
        },
        {
          title: 'Tags',
          key: 'tags',
          dataIndex: 'tags',
          render: (text, aliment) => {
          var color, tag;

              if (aliment.calories <= 100) {
                  color = 'green';
                   tag = 'low calorie dense';
              } else if (aliment.calories > 100 && aliment.calories <= 400) {
                  color = 'yellow';
                  tag = 'medium calorie dense';
              } else {
                  color = 'red';
                  tag = 'high calorie dense';
              }
              return <Tag color = {color} key = {tag}>{tag.toUpperCase()}</Tag>;

          }
        },
        {
              title: 'Actions',
              dataIndex: 'actions',
              key: 'actions',
              render: (text, aliment) => <Radio.Group>
                                                 <Radio.Button onClick = {() => {setAlimentName(aliment.name); setShowUserAlimentsDrawer(!showUserAlimentsDrawer)}} >Consume</Radio.Button>
                                                 <Radio.Button onClick = {() => {removeAliment(aliment.name)}} >Delete Aliment</Radio.Button>
                                         </Radio.Group>
        },
    ];

    const fetchAllAliments = () =>

        getAllAliments()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setAliments(data);

            }).catch((err) => {
                                                          //var status = err.response.status;
                                                          err.response.json().then((res) => {
                                                                errorNotificationWithIcon("There was an issue", `${res.message} [${err.response.status}] [${res.httpStatus}]`);
                                                          })
                                                          });





    useEffect(() => {
        if (secondPage) {
         console.log("component mounted");
          fetchAllAliments();
          }


        },[secondPage])


    if (secondPage) {
        return <>
        <AlimentsDrawerForm showAlimentsDrawer = {showAlimentsDrawer} setShowAlimentsDrawer = {setShowAlimentsDrawer} fetchAllAliments = {fetchAllAliments}/>
        <UserAlimentsDrawerForm showUserAlimentsDrawer = {showUserAlimentsDrawer} setShowUserAlimentsDrawer = {setShowUserAlimentsDrawer} userName = {email} alimentName = {alimentName}/>
        <Table dataSource={aliments} columns={columns} bordered title= {() => <div><Button  onClick={() => setShowAlimentsDrawer(!showAlimentsDrawer)} type="primary" shape="round" icon = {<PlusOutlined />}  size="small">
                                                                                   Add Aliment
                                                                               </Button> <center>Aliments</center></div>} rowKey={aliment => aliment.name} />
        </>



    }

}

export default SecondPage;