const mongoose = require("mongoose");

const notificationSchema = new mongoose.Schema(
  {
    content: { type: String, required: true },
    user_id: { type: mongoose.Schema.Types.ObjectId, ref: "User", required: true },
    bill_id: { type: mongoose.Schema.Types.ObjectId, ref: "Bill", required: true },
    isRead: { type: Boolean, required: true, default: false },
  },
  { timestamps: true }
);
const Notification =  mongoose.model("Notification", notificationSchema);

module.exports = Notification;

