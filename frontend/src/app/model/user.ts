import { Announcement } from "./announcement";
import { Role } from "./role";

export interface User {
  id: number;
  username: string;
  password: string;
  name: string;
  lastName: string;
  imageUrl: string;
  phone: string;
  email: string;
  roles: Role[];
  createdAnnouncements: Announcement[];
}