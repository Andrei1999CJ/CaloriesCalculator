import { notification } from "antd";

const openNotificationWithIcon = (type, message, description, placement) => {
    placement = placement || "topRight";
   notification[type]({
      message,
      description,
      placement
    });
  };


export const successNotificationWithIcon = (message, description, placement) => {
    openNotificationWithIcon('success', message, description, placement);
}

export const errorNotificationWithIcon = (message, description, placement) => {
    openNotificationWithIcon('error', message, description, placement);
}