FROM node:boron
#CMD "sh" "ng serve"

# Create app directory
RUN mkdir -p /usr/src/app
RUN mkdir -p /usr/src/app/src
WORKDIR /usr/src/app

# Install app dependencies
COPY package.json /usr/src/app/

RUN npm uninstall -g angular-cli @angular/cli
RUN npm cache clean

# Bundle app source
COPY src /usr/src/app/src
COPY .angular-cli.json /usr/src/app
COPY tsconfig.json /usr/src/app

RUN npm install
#RUN npm install -g @angular/cli

#RUN export PATH="/usr/src/app/node_modules/@angular/cli/bin:$PATH" 
#RUN ls -lrt /usr/src/app/node_modules/@angular/cli/bin

#RUN "ls" "-lrt"
RUN ls -lrt
RUN pwd


EXPOSE 4200
CMD /usr/src/app/node_modules/@angular/cli/bin/ng serve --host 0.0.0.0 --base-href=/DEF/ --output-path=/DEF/ --deploy-url=/DEF/ --disable-host-check true
