FROM node:boron
#CMD "sh" "ng serve"

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

RUN npm cache clean

# Bundle app source
COPY . /usr/src/app

RUN npm install

#RUN "ls" "-lrt"
RUN ls -lrt
RUN pwd

EXPOSE 3000
CMD node scripts/start.js
