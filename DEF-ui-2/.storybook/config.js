import { configure } from '@kadira/storybook';

function loadStories() {
    require('../src/components/stories/index.js');
}

configure(loadStories, module);
