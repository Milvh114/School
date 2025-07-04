import { useState, useEffect } from "react";
import { alpha } from "@mui/material/styles";
import {
  Avatar,
  Badge,
  Box,
  Divider,
  IconButton,
  List,
  ListItemAvatar,
  ListItemButton,
  ListItemText,
  Popover,
  Typography,
  Tooltip,
} from "@mui/material";
import Iconify from "../../../components/iconify";
import axios from "axios";
import { apiUrl, routes, methods } from "../../../constants";
import { useAuthStore } from "../../../store/authStore";
import toast from "react-hot-toast";



// Dữ liệu mẫu cho thông báo


export default function NotificationsPopover() {
  const [notifications, setNotifications] = useState([]);
  const [open, setOpen] = useState(null);
  const { user } = useAuthStore();

  // const totalUnRead = notifications.filter((item) => item.isUnread).length;
  const [totalUnRead, setTotalUnRead] = useState(0);


  const handleOpen = (event) => {
    setOpen(event.currentTarget);
  };

  const handleClose = () => {
    setOpen(null);
  };

  const handleMarkAllAsRead = () => {
    setNotifications(
      notifications.map((notification) => ({
        ...notification,
        isUnread: false,
      }))
    );
  };


  /////////handle noti
  const handleNoti = () => {


    axios
    .get(apiUrl(routes.NOTI, methods.GET_ALL, user._id))
    .then((response) => {
      // const notifications = response.data.notisList
      // console.log("notifications: ", notifications);
      // setNotifications()

      const notifications = response.data.notisList.map((noti, index) => ({
        id: noti._id,
        title: "Thông báo thanh toán",
        description: `${noti.content} billID: ${noti.bill_id}`,
        avatar: null,
        type: "payment",
        createdAt: new Date(),
        isRead: noti.isRead,
      }));

      console.log("Formatted notifications: ", notifications);
      setNotifications(notifications); //
      setTotalUnRead(notifications.filter((item) => !item.isRead).length);

    })
    .catch((response) => {
      console.log(response)
      console.error("Error create payment link:",response.response.data.message);
      toast.error(`Failed: ${response.response.data.message}`);
    });

  }

  const handleMarkAsRead = async (notificationId) => {
    console.log(notificationId);
    try {
      await axios.put(apiUrl(routes.NOTI, methods.PUT, notificationId), { isRead: true });
  
      // Cập nhật state notifications
      setNotifications((prevNotifications) => {
        const updatedNotifications = prevNotifications.map((noti) =>
          noti.id === notificationId ? { ...noti, isRead: true } : noti
        );
      toast("read notification")
        // Cập nhật totalUnRead ngay sau khi notifications thay đổi
        setTotalUnRead(updatedNotifications.filter((item) => !item.isRead).length);
  
        return updatedNotifications;
      });
  
    } catch (error) {
      console.error("Error updating notification:", error);
      toast.error(`Lỗi: ${error?.response?.data?.message || "Không thể cập nhật thông báo"}`);
    }
  };
  

  useEffect(() => {

    handleNoti()
  }, []);

  return (
    <>
      <IconButton
        onClick={handleOpen}
        sx={{
          p: 1,
          ...(open && {
            bgcolor: (theme) => alpha(theme.palette.primary.main, 0.16),
          }),
        }}
      >
        <Badge badgeContent={totalUnRead} color="error">
          <Iconify icon="eva:bell-fill" width={24} height={24} />
        </Badge>
      </IconButton>

      <Popover
        open={Boolean(open)}
        anchorEl={open}
        onClose={handleClose}
        anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
        transformOrigin={{ vertical: "top", horizontal: "right" }}
        PaperProps={{
          sx: {
            mt: 1.5,
            ml: 0.75,
            width: 360,
            '& .MuiMenuItem-root': {
              px: 1.5,
              py: 1,
              typography: 'body2',
              borderRadius: 0.75,
            },
          },
        }}
      >
        <Box sx={{ display: 'flex', alignItems: 'center', py: 2, px: 2.5 }}>
          <Box sx={{ flexGrow: 1 }}>
            <Typography variant="subtitle1">Thông báo</Typography>
            <Typography variant="body2" sx={{ color: 'text.secondary' }}>
              Bạn có {totalUnRead} thông báo chưa đọc
            </Typography>
          </Box>

          {totalUnRead > 0 && (
            <Tooltip title="Đánh dấu đã đọc tất cả">
              <IconButton color="primary" onClick={handleMarkAllAsRead}>
                <Iconify icon="eva:done-all-fill" width={20} height={20} />
              </IconButton>
            </Tooltip>
          )}
        </Box>

        <Divider sx={{ borderStyle: 'dashed' }} />

        <List
          disablePadding
          sx={{ height: { xs: 340, sm: 400 }, overflow: 'auto' }}
        >
          {notifications.map((notification) => (
            <NotificationItem key={notification.id} notification={notification} handleMarkAsRead={handleMarkAsRead}/>
          ))}
        </List>
      </Popover>
    </>
  );
}

// ----------------------------------------------------------------------

function NotificationItem({ notification, handleMarkAsRead }) {
  const { avatar, title, description, type, isRead } = notification;

  // Xác định icon dựa vào loại thông báo
  const getIcon = (type) => {
    switch (type) {
      case 'booking':
        return 'eva:calendar-fill';
      case 'payment':
        return 'eva:credit-card-fill';
      case 'reminder':
        return 'eva:clock-fill';
      case 'promotion':
        return 'eva:gift-fill';
      default:
        return 'eva:info-fill';
    }
  };

  // Xác định màu dựa vào loại thông báo
  const getColor = (type) => {
    switch (isRead) {
      case false:
        return 'primary';
      case true:
        return 'success';
      case 'reminder':
        return 'warning';
      case 'promotion':
        return 'error';
      default:
        return 'info';
    }
  };

  return (
    <ListItemButton
    onClick={() => handleMarkAsRead(notification.id)}

      sx={{
        py: 1.5,
        px: 2.5,
        mt: '1px',
        ...(isRead && {
          bgcolor: 'action.hover',
        }),
      }}
    >
      <ListItemAvatar>
        <Avatar sx={{ bgcolor: `${getColor(type)}.lighter` }}>
          <Iconify icon={getIcon(type)} width={24} height={24} color={`${getColor(type)}.main`} />
        </Avatar>
      </ListItemAvatar>
      <ListItemText
        primary={title}
        secondary={
          <Typography
            variant="caption"
            sx={{
              mt: 0.5,
              display: 'flex',
              alignItems: 'center',
              color: 'text.disabled',
            }}
          >
            {description}
          </Typography>
        }
      />
    </ListItemButton>
  );
}