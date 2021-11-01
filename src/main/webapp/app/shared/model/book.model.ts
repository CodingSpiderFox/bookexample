import dayjs from 'dayjs';
import { IAuthor } from 'app/shared/model/author.model';

export interface IBook {
  id?: number;
  price?: number;
  title?: string;
  writeStartTimestamp?: string | null;
  publishTimestamp?: string | null;
  authors?: IAuthor[];
}

export const defaultValue: Readonly<IBook> = {};
